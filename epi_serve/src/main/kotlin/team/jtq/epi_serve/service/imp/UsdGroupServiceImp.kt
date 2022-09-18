package team.jtq.epi_serve.service.imp

import com.alibaba.fastjson.JSON
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import team.jtq.epi_serve.entity.UsdGroup
import team.jtq.epi_serve.entity.UsdGroupUser
import team.jtq.epi_serve.entity.ao.GroupUpLoadeEntity
import team.jtq.epi_serve.entity.ao.ModifyGroupEntity
import team.jtq.epi_serve.entity.ao.ResultStatusCode
import team.jtq.epi_serve.entity.ao.UserGroupSwapEntity
import team.jtq.epi_serve.mapper.UsdGroupMapper
import team.jtq.epi_serve.mapper.UsdGroupUserMapper
import team.jtq.epi_serve.service.TokenService
import team.jtq.epi_serve.service.UsdGroupService
import team.jtq.epi_serve.service.UsdLinkService
import team.jtq.epi_serve.tools.Result
import java.time.LocalDateTime
import kotlin.reflect.full.declaredMemberProperties

@Service
class UsdGroupServiceImp : ServiceImpl<UsdGroupMapper, UsdGroup>(), UsdGroupService {

    @Autowired
    private lateinit var tokenService: TokenService

    @Autowired
    private lateinit var linkService: UsdLinkService

    override fun addGroup(entity: GroupUpLoadeEntity): Result {
        val tokenCheckRes = tokenService.getUserParameter(entity.token)
        if (tokenCheckRes.code != ResultStatusCode.OK.code)
            return Result.error(ResultStatusCode.INVALID_TOKEN)
        val obj = UsdGroup::class.java
        val instant = obj.newInstance()
        val systemTime = LocalDateTime.now()
        val json = JSON.parseObject(JSON.toJSONString(tokenCheckRes.data))

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

    override fun joinGroup(entity: UserGroupSwapEntity): Result {
        val tokenCheckRes = tokenService.getUserParameter(entity.token)
        if (tokenCheckRes.code != ResultStatusCode.OK.code)
            return Result.error(ResultStatusCode.INVALID_TOKEN)

        val json = JSON.parseObject(JSON.toJSONString(tokenCheckRes.data))
        val linkRes = linkService.addLinkinBeans(
            UsdGroupUserMapper::class,
            UsdGroupUser::class,
            Pair(UsdGroupUser::groupId.name, entity.gid),
            Pair(UsdGroupUser::uid.name, json["user_id"] as String)
        )
        return if (linkRes) {
            Result.ok()
        } else {
            Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        }
    }

    override fun exitGroup(entity: UserGroupSwapEntity): Result {
        val tokenCheckRes = tokenService.getUserParameter(entity.token)
        if (tokenCheckRes.code != ResultStatusCode.OK.code)
            return Result.error(ResultStatusCode.INVALID_TOKEN)
        val query = KtQueryWrapper(UsdGroup::class.java)
        query.eq(UsdGroup::createUser, entity.gid)
        val groupCreate = this.baseMapper.selectOne(query)
        val json = JSON.parseObject(JSON.toJSONString(tokenCheckRes.data))
        if (groupCreate == null)
            return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        if (groupCreate.createUser == json["user_id"] as String)
            return Result.error("创建者不可退出群组！")
        val res = linkService.deleteLinkinBeans(
            UsdGroupUserMapper::class,
            UsdGroupUser::class,
            listOf(Pair(UsdGroupUser::groupId, entity.gid), Pair(UsdGroupUser::uid, json["user_id"] as String))
        )
        return if (res)
            Result.ok()
        else
            Result.error(ResultStatusCode.SERVICE_INNER_ERR)
    }

    override fun revokedGroup(entity: UserGroupSwapEntity): Result {
        val tokenCheckRes = tokenService.getUserParameter(entity.token)
        if (tokenCheckRes.code != ResultStatusCode.OK.code)
            return Result.error(ResultStatusCode.INVALID_TOKEN)

        val groupMembers = linkService.selectLinkinBeans(
            UsdGroupUserMapper::class,
            UsdGroupUser::class,
            listOf(Pair(UsdGroupUser::groupId, entity.gid))
        ) ?: return Result.error("无效操作")
        if (groupMembers.size > 1) {
            return Result.error("群组内还有成员，不可删除!")
        }
        var res = linkService.deleteLinkinBeans(
            UsdGroupUserMapper::class,
            UsdGroupUser::class,
            listOf(Pair(UsdGroupUser::groupId, entity.gid))
        )
        if (!res)
            return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        res = linkService.deleteLinkinBeans(
            UsdGroupMapper::class,
            UsdGroup::class,
            listOf(Pair(UsdGroup::id, entity.gid))
        )
        if (!res)
            return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        return Result.ok()
    }

    override fun modifyGroup(entity: ModifyGroupEntity): Result {
        val tokenCheckRes = tokenService.getUserParameter(entity.token)
        if (tokenCheckRes.code != ResultStatusCode.OK.code)
            return Result.error(ResultStatusCode.INVALID_TOKEN)
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
        val tokenCheckRes = tokenService.getUserParameter(token)
        if (tokenCheckRes.code != ResultStatusCode.OK.code)
            return Result.error(ResultStatusCode.INVALID_TOKEN)
        val json = JSON.parseObject(JSON.toJSONString(tokenCheckRes.data))
        val userId = json["user_id"] as String
        val res = linkService.selectLinkinBeans(
            UsdGroupUserMapper::class,
            UsdGroupUser::class,
            listOf()
        )
        TODO("Not yet implemented")
    }

    override fun selectUserCreatedGroup(token: String): Result {
        TODO("Not yet implemented")
    }


}