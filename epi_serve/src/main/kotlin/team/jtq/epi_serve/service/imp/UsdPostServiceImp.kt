package team.jtq.epi_serve.service.imp

import com.alibaba.fastjson.JSON
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import team.jtq.epi_serve.entity.UsdGroupPost
import team.jtq.epi_serve.entity.UsdPost
import team.jtq.epi_serve.entity.UsdUserPost
import team.jtq.epi_serve.entity.ao.PostUpLoadeEntity
import team.jtq.epi_serve.entity.ao.ResultStatusCode
import team.jtq.epi_serve.mapper.UsdGroupPostMapper
import team.jtq.epi_serve.mapper.UsdPostMapper
import team.jtq.epi_serve.mapper.UsdUserPostMapper
import team.jtq.epi_serve.service.TokenService
import team.jtq.epi_serve.service.UsdLinkService
import team.jtq.epi_serve.service.UsdPostService
import team.jtq.epi_serve.tools.Result
import java.time.LocalDateTime

@Service
class UsdPostServiceImp : ServiceImpl<UsdPostMapper, UsdPost>(), UsdPostService {

    @Autowired
    private lateinit var tokenService: TokenService

    @Autowired
    private lateinit var linkService: UsdLinkService

    override fun addUserPost(entity: PostUpLoadeEntity): Result {
        val tokenCheckRes = tokenService.getUserParameter(entity.token)
        if (tokenCheckRes.code != ResultStatusCode.OK.code) {
            return Result.error(ResultStatusCode.INVALID_TOKEN)
        }
        val json = JSON.parseObject(JSON.toJSONString(tokenCheckRes.data))
        val obj = UsdPost::class.java
        val instant = obj.newInstance()
        val systemTime = LocalDateTime.now()

        instant.createTime = systemTime
        instant.favorites = 0
        instant.postContent = entity.connect
        instant.likes = 0
        instant.status = 1

        super<ServiceImpl>.save(instant)
        try {
            val link1Res = linkService.addLinkinBeans(
                UsdUserPostMapper::class,
                UsdUserPost::class,
                Pair(UsdUserPost::postId.name, instant.id),
                Pair(UsdUserPost::uid.name, json["user_id"] as String)
            )
            if (!link1Res)
                return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
            val link2Res = linkService.addLinkinBeans(
                UsdGroupPostMapper::class, UsdGroupPost::class,
                Pair(UsdGroupPost::groupId.name, entity.groupId), Pair(UsdGroupPost::postId.name, instant.id)
            )
            if (!link2Res)
                return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        } catch (e: Exception) {
            return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
        }
        return Result.ok()
    }

}