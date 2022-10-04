package team.jtq.epi_serve.service.imp

import com.alibaba.fastjson.JSONArray
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.DigestUtils
import team.jtq.epi_serve.config.BeanContext
import team.jtq.epi_serve.entity.UsdGroup
import team.jtq.epi_serve.entity.UsdGroupPost
import team.jtq.epi_serve.entity.UsdGroupUser
import team.jtq.epi_serve.entity.UsdPost
import team.jtq.epi_serve.entity.ao.GroupUpLoadeEntity
import team.jtq.epi_serve.entity.ao.ModifyGroupEntity
import team.jtq.epi_serve.entity.ao.ResultStatusCode
import team.jtq.epi_serve.mapper.UsdGroupMapper
import team.jtq.epi_serve.mapper.UsdGroupPostMapper
import team.jtq.epi_serve.mapper.UsdGroupUserMapper
import team.jtq.epi_serve.mapper.UsdPostMapper
import team.jtq.epi_serve.service.TokenService
import team.jtq.epi_serve.service.UsdGroupService
import team.jtq.epi_serve.service.MapperReflectionService
import team.jtq.epi_serve.tools.Result
import team.jtq.epi_serve.tools.adapterGroupView
import team.jtq.epi_serve.tools.adapterPostView
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit
import javax.annotation.Resource
import kotlin.reflect.full.declaredMemberProperties

@Service
class UsdGroupServiceImp : ServiceImpl<UsdGroupMapper, UsdGroup>(), UsdGroupService {

    @Resource
    private lateinit var redisTemplate: RedisTemplate<String, Any>

    @Autowired
    private lateinit var linkService: MapperReflectionService

    @Autowired
    private lateinit var tokenService: TokenService

    private val USER_JOINED_GROUP ="USER_JOINED_GROUP"
    private val GROUPS = "GROUPS"

    @Transactional
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
        this.baseMapper.insert(instant)
        joinGroup(token,instant.id)
        return Result.ok()
    }

    @Transactional
    override fun joinGroup(token: String, gid: String): Result {
        val redisKey = USER_JOINED_GROUP+":"+DigestUtils.md5DigestAsHex(token.toByteArray(Charsets.UTF_8))
        if(redisTemplate.hasKey(redisKey)){ //强制刷新
            redisTemplate.delete(redisKey)
        }
        val json = tokenService.getUserInfo(token) ?: return Result.error(ResultStatusCode.TOKEN_EXPIRED)
        val mapper = BeanContext.getBeanbyClazz(UsdGroupUserMapper::class.java)
        val obj = UsdGroupUser::class.java.newInstance()
        obj.groupId = gid
        obj.uid = json["user_id"] as String
        obj.joinedTime = LocalDateTime.now()
        mapper.insert(obj)
        return Result.ok()
    }

    @Transactional
    override fun exitGroup(token: String, gid: String): Result {
        val json = tokenService.getUserInfo(token) ?: return Result.error(ResultStatusCode.TOKEN_EXPIRED)
        val userId = json["user_id"] as String
        val redisKey = USER_JOINED_GROUP+":"+DigestUtils.md5DigestAsHex(token.toByteArray(Charsets.UTF_8))
        if(redisTemplate.hasKey(redisKey)){
            redisTemplate.delete(redisKey)
        }
        val query = KtQueryWrapper(UsdGroup::class.java)
        query.eq(UsdGroup::createUser, userId).eq(UsdGroup::id,gid)
        val groupCreate = this.baseMapper.selectOne(query)
        if(groupCreate == null){
            val res = linkService.deleteLinkinBeans(
                UsdGroupUserMapper::class,
                UsdGroupUser::class,
                listOf(Pair(UsdGroupUser::groupId, gid), Pair(UsdGroupUser::uid, json["user_id"] as String))
            )
            return if (res)
                Result.ok()
            else
                throw RuntimeException("Sql Error!")
        }else{
            if (groupCreate.createUser == json["user_id"] as String)
                return Result.error("创建者不可退出群组！")
        }
        return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
    }

    @Transactional
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
           throw RuntimeException("Sql Error!")
        return Result.ok()
    }

    @Transactional
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
            throw RuntimeException("Sql Error!")
        }
    }

    override fun selectUserJoinedGroup(token: String, pageIndex: String, pageItems: String): Result {
        val json = tokenService.getUserInfo(token) ?: return Result.error(ResultStatusCode.TOKEN_EXPIRED)
        val userId = json["user_id"] as String
        val redisKey = USER_JOINED_GROUP+":"+DigestUtils.md5DigestAsHex(token.toByteArray(Charsets.UTF_8))
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
            redisTemplate.opsForValue().set(redisKey,JSONArray.toJSONString(groupId),1,TimeUnit.HOURS)
        }
        val page = Page<UsdGroup>(pageIndex.toLong(),pageItems.toLong())
        val query =KtQueryWrapper(UsdGroup::class.java)
        query.`in`(UsdGroup::id,groupId)
        val groups = this.baseMapper.selectPage(page,query)
        val res = adapterGroupView(groups.records)
        return Result.ok(res)
    }

    override fun selectUserJoinedGroup(token: String): Result {
        val json = tokenService.getUserInfo(token) ?: return Result.error(ResultStatusCode.TOKEN_EXPIRED)
        val userId = json["user_id"] as String
        val redisKey = USER_JOINED_GROUP+":"+DigestUtils.md5DigestAsHex(token.toByteArray(Charsets.UTF_8))
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
            redisTemplate.opsForValue().set(redisKey,JSONArray.toJSONString(groupId),1,TimeUnit.HOURS)
        }
        val query =KtQueryWrapper(UsdGroup::class.java)
        query.`in`(UsdGroup::id,groupId)
        val groups = this.baseMapper.selectList(query)
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

    override fun selectGroupPost(token: String, gid: String, pageIndex: String, pageItems: String): Result {
        val redisKey = GROUPS+":"+DigestUtils.md5DigestAsHex(gid.toByteArray(Charsets.UTF_8))
        val postsId:List<String>
        if(redisTemplate.hasKey(redisKey)){
            val obj = redisTemplate.opsForValue().get(redisKey)
            postsId = JSONArray.parseArray(obj as String,String::class.java)
        }
        else{
             postsId = linkService.selectLinkinBeans(
                 UsdGroupPostMapper::class,
                 UsdGroupPost::class,
                 listOf(Pair(UsdGroupPost::groupId,gid))
             )?.map { it.postId }?:return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
            redisTemplate.opsForValue().set(redisKey,JSONArray.toJSONString(postsId),10,TimeUnit.MINUTES)
        }
        val page = Page<UsdPost>(pageIndex.toLong(),pageItems.toLong())
        val posts = linkService.batchSelectLinkBeansInListOnPage(
            UsdPostMapper::class,
            UsdPost::class,
            Pair(UsdPost::id,postsId),
            page
        )?:return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        val res = adapterPostView(posts.records)?:return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        return Result.ok(res)
    }

    override fun selectUnJoinedGroup(token: String, pageIndex: String, pageItems: String): Result {
        val joinedGroup = this.selectUserJoinedGroup(token).data as List<UsdGroup>
        val page = Page<UsdGroup>(pageIndex.toLong(),pageItems.toLong())
        val groupId= joinedGroup.map { it.id }
        val query = KtQueryWrapper(UsdGroup::class.java)
        query.notIn(UsdGroup::id,groupId)
        val groups = this.baseMapper.selectPage(page,query).records.filter { it.id !="0" }
        val res = adapterGroupView(groups)
        return Result.ok(res)
    }

    override fun getGroupBriefInfo(token: String, gid: String): Result {

        TODO("Not yet implemented")
    }
}