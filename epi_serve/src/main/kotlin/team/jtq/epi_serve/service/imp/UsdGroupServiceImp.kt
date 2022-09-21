package team.jtq.epi_serve.service.imp

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils
import team.jtq.epi_serve.entity.UsdGroup
import team.jtq.epi_serve.entity.UsdGroupUser
import team.jtq.epi_serve.entity.ao.GroupUpLoadeEntity
import team.jtq.epi_serve.entity.ao.ModifyGroupEntity
import team.jtq.epi_serve.entity.ao.ResultStatusCode
import team.jtq.epi_serve.mapper.UsdGroupMapper
import team.jtq.epi_serve.mapper.UsdGroupUserMapper
import team.jtq.epi_serve.service.TokenService
import team.jtq.epi_serve.service.UsdGroupService
import team.jtq.epi_serve.service.UsdLinkService
import team.jtq.epi_serve.tools.Result
import java.security.acl.Group
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit
import javax.annotation.Resource
import kotlin.reflect.full.declaredMemberProperties

@Service
class UsdGroupServiceImp : ServiceImpl<UsdGroupMapper, UsdGroup>(), UsdGroupService {

    @Resource
    private lateinit var redisTemplate: RedisTemplate<String, Any>

    @Autowired
    private lateinit var linkService: UsdLinkService

    @Autowired
    private lateinit var tokenService: TokenService

    private val USER_JOINED_GROUP ="USER_JOINED_GROUP"

    override fun addGroup(entity: GroupUpLoadeEntity, token: String): Result {

        val obj = UsdGroup::class.java
        val instant = obj.newInstance()
        val systemTime = LocalDateTime.now()

        val json = tokenService.getUserInfo(token) ?: return Result.error(ResultStatusCode.TOKEN_EXPIRED)

        instant.createTime = systemTime
        instant.groupDescription = entity.groupDescription
        instant.groupName = entity.groupName
        instant.groupIco = entity.groupIco
        instant.createUser = json["user_id"] as String

        val linkRes: Boolean = linkService.addLinkinBeans(
            UsdGroupUserMapper::class,
            UsdGroupUser::class,
            Pair(UsdGroupUser::groupId.name, instant.id),
            Pair(UsdGroupUser::uid.name, instant.createUser)
        )
        if (!linkRes)
            return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        return Result.ok()
    }

    override fun joinGroup(token: String, gid: String): Result {

        val json = tokenService.getUserInfo(token) ?: return Result.error(ResultStatusCode.TOKEN_EXPIRED)

        val linkRes = linkService.addLinkinBeans(
            UsdGroupUserMapper::class,
            UsdGroupUser::class,
            Pair(UsdGroupUser::groupId.name, gid),
            Pair(UsdGroupUser::uid.name, json["user_id"] as String)
        )
        return if (linkRes) {
            Result.ok()
        } else {
            Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        }
    }

    override fun exitGroup(token: String, gid: String): Result {

        val json = tokenService.getUserInfo(token) ?: return Result.error(ResultStatusCode.TOKEN_EXPIRED)

        val query = KtQueryWrapper(UsdGroup::class.java)
        query.eq(UsdGroup::createUser, gid)
        val groupCreate = this.baseMapper.selectOne(query)
        if (groupCreate == null)
            return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        if (groupCreate.createUser == json["user_id"] as String)
            return Result.error("创建者不可退出群组！")
        val res = linkService.deleteLinkinBeans(
            UsdGroupUserMapper::class,
            UsdGroupUser::class,
            listOf(Pair(UsdGroupUser::groupId, gid), Pair(UsdGroupUser::uid, json["user_id"] as String))
        )
        return if (res)
            Result.ok()
        else
            Result.error(ResultStatusCode.SERVICE_INNER_ERR)
    }

    override fun revokedGroup(token: String, gid: String): Result {

        val groupMembers = linkService.selectLinkinBeans(
            UsdGroupUserMapper::class,
            UsdGroupUser::class,
            listOf(Pair(UsdGroupUser::groupId, gid))
        ) ?: return Result.error("无效操作")
        if (groupMembers.size > 1) {
            return Result.error("群组内还有成员，不可删除!")
        }
        var res = linkService.deleteLinkinBeans(
            UsdGroupUserMapper::class,
            UsdGroupUser::class,
            listOf(Pair(UsdGroupUser::groupId, gid))
        )
        if (!res)
            return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        res = linkService.deleteLinkinBeans(
            UsdGroupMapper::class,
            UsdGroup::class,
            listOf(Pair(UsdGroup::id, gid))
        )
        if (!res)
            return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        return Result.ok()
    }

    override fun modifyGroup(entity: ModifyGroupEntity, token: String): Result {
        return try {
            val query = KtUpdateWrapper(UsdGroup::class.java)
            val obj = UsdGroup::class
            val objectProperty = obj.declaredMemberProperties
            query.eq(UsdGroup::id, entity::gid)
            objectProperty.forEach {
                if (entity.change.containsKey(it.name)) {
                    query.set(it, entity.change[it.name])
                }
            }
            super<ServiceImpl>.update(query)
            Result.ok()
        } catch (e: Exception) {
            Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        }
    }

    override fun selectUserJoinedGroup(token: String): Result {
        val json = tokenService.getUserInfo(token) ?: return Result.error(ResultStatusCode.TOKEN_EXPIRED)
        val userId = json["user_id"] as String
        val redisKey = USER_JOINED_GROUP+":"+DigestUtils.md5DigestAsHex(token.toByteArray(Charsets.UTF_8))
        val groups:List<UsdGroup>
        val groupId:List<String>

        if(redisTemplate.hasKey(redisKey)){
            val obj = redisTemplate.opsForValue().get(redisKey) as String
            groupId = JSONArray.parseArray(obj,String::class.java)

        }else{
            val res = linkService.selectLinkinBeans(
                UsdGroupUserMapper::class,
                UsdGroupUser::class,
                listOf(Pair(UsdGroupUser::uid, userId))
            ) ?: return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
            groupId = res.map { it.groupId }
            val obj = JSON.toJSONString(groupId)
            redisTemplate.opsForValue().set(redisKey,obj,1,TimeUnit.HOURS)
        }
        val query =KtQueryWrapper(UsdGroup::class.java)
        query.`in`(UsdGroup::id,groupId)
        groups = this.baseMapper.selectList(query)
        return Result.ok(groups)
    }

    override fun selectUserCreatedGroup(token: String): Result {
        val json = tokenService.getUserInfo(token) ?: return Result.error(ResultStatusCode.TOKEN_EXPIRED)
        val userId = json["user_id"] as String
        val query = KtQueryWrapper(UsdGroup::class.java)
        query.eq(UsdGroup::createUser, userId)
        val beanList = this.baseMapper.selectList(query)
        return Result.ok(beanList)
    }

    override fun selectUnJoinedGroup(token: String): Result {
        val joinedGroup = this.selectUserJoinedGroup(token).data as List<UsdGroup>
        val groupId= joinedGroup.map { it.id }
        val query = KtQueryWrapper(UsdGroup::class.java)
        query.notIn(UsdGroup::id,groupId)
        val groups = this.baseMapper.selectList(query)
        return Result.ok(groups)
    }
}