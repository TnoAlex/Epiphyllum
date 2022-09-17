package team.jtq.epi_serve.service.imp

import com.alibaba.fastjson.JSON
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import team.jtq.epi_serve.entity.UsdGroup
import team.jtq.epi_serve.entity.UsdGroupUser
import team.jtq.epi_serve.entity.ao.GroupUpLoadeEntity
import team.jtq.epi_serve.entity.ao.ResultStatusCode
import team.jtq.epi_serve.entity.ao.UserGroupSwapEntity
import team.jtq.epi_serve.mapper.UsdGroupMapper
import team.jtq.epi_serve.mapper.UsdGroupUserMapper
import team.jtq.epi_serve.service.TokenService
import team.jtq.epi_serve.service.UsdGroupService
import team.jtq.epi_serve.service.UsdLinkService
import team.jtq.epi_serve.tools.Result
import java.time.LocalDateTime

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
        query.eq(UsdGroup::createUser,entity.gid)
        val groupCreater = this.baseMapper.selectOne(query)
        val json = JSON.parseObject(JSON.toJSONString(tokenCheckRes.data))
        if(groupCreater ==null)
            return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        if(groupCreater.createUser == json["user_id"] as String)
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

}