package team.jtq.epi_serve.service.imp

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.DigestUtils
import team.jtq.epi_serve.config.AppResourceConfig
import team.jtq.epi_serve.config.BeanContext
import team.jtq.epi_serve.entity.*
import team.jtq.epi_serve.entity.ao.PostUpLoadeEntity
import team.jtq.epi_serve.entity.ao.ResultStatusCode
import team.jtq.epi_serve.mapper.UsdGroupPostMapper
import team.jtq.epi_serve.mapper.UsdPostMapper
import team.jtq.epi_serve.mapper.UsdUserFavoritesMapper
import team.jtq.epi_serve.mapper.UsdUserPostMapper
import team.jtq.epi_serve.service.TokenService
import team.jtq.epi_serve.service.UsdGroupService
import team.jtq.epi_serve.service.UsdLinkService
import team.jtq.epi_serve.service.UsdPostService
import team.jtq.epi_serve.tools.Result
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit
import kotlin.reflect.KType

@Service
class UsdPostServiceImp : ServiceImpl<UsdPostMapper, UsdPost>(), UsdPostService {

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, Any>

    @Autowired
    private lateinit var tokenService: TokenService

    @Autowired
    private lateinit var linkService: UsdLinkService

    private val USER_FAV_IDS = "USER_FAV_IDS"
    private val USER_ALL_IDS = "USER_ALL_IDS"
    private val POST_CACHE = "POST_CACHE"

    @Transactional
    override fun addUserPost(entity: PostUpLoadeEntity, token: String): Result {
        val json = tokenService.getUserInfo(token) ?: return Result.error(ResultStatusCode.TOKEN_EXPIRED)
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
                UsdGroupPostMapper::class,
                UsdGroupPost::class,
                Pair(UsdGroupPost::groupId.name, entity.groupId), Pair(UsdGroupPost::postId.name, instant.id)
            )
            if (!link2Res)
                throw RuntimeException("Sql Error!")
        } catch (e: Exception) {
            throw RuntimeException("Sql Error!")
        }
        return Result.ok()
    }

    @Transactional
    override fun deleteUserPost(token: String, pid: String): Result {

        var res = linkService.deleteLinkinBeans(
            UsdGroupPostMapper::class,
            UsdGroupPost::class,
            listOf(Pair(UsdGroupPost::postId, pid))
        )
        if (!res)
            throw RuntimeException("Sql Error!")
        res = linkService.deleteLinkinBeans(
            UsdUserPostMapper::class,
            UsdUserPost::class,
            listOf(Pair(UsdUserPost::postId, pid))
        )
        if (!res)
            throw RuntimeException("Sql Error!")
        val query = KtQueryWrapper(UsdPost::class.java)
        query.eq(UsdPost::id, pid)
        this.baseMapper.delete(query)
        return Result.ok()
    }

    @Transactional
    override fun favoritePosts(token: String, pid: String): Result {
        val json = tokenService.getUserInfo(token) ?: return Result.error(ResultStatusCode.TOKEN_EXPIRED)
        val user = json["user_id"] as String
        val res = linkService.addLinkinBeans(
            UsdUserFavoritesMapper::class,
            UsdUserFavorites::class,
            Pair(UsdUserFavorites::postId.name, pid),
            Pair(UsdUserFavorites::uid.name, user)
        )
        return if (res)
            Result.ok()
        else
            throw RuntimeException("Sql Error!")
    }

    @Transactional
    override fun unfavoritePost(token: String, pid: String): Result {
        val json = tokenService.getUserInfo(token) ?: return Result.error(ResultStatusCode.TOKEN_EXPIRED)
        val user = json["user_id"] as String
        val res = linkService.deleteLinkinBeans(
            UsdUserFavoritesMapper::class,
            UsdUserFavorites::class,
            listOf(Pair(UsdUserFavorites::postId, pid), Pair(UsdUserFavorites::uid, user))
        )
        return if (res)
            Result.ok()
        else
            throw RuntimeException("Sql Error!")
    }

    override fun selectAllFavorite(token: String, pageIndex: Long, pageItems: Long): Result {
        AppResourceConfig.forceRefreshFolder(POST_CACHE)
        val json = tokenService.getUserInfo(token) ?: return Result.error(ResultStatusCode.TOKEN_EXPIRED)
        val page = Page<UsdPost>(pageIndex, pageItems)
        val query = KtQueryWrapper(UsdPost::class.java)
        val redisKey = USER_FAV_IDS + ":"+DigestUtils.md5DigestAsHex(token.toByteArray(Charsets.UTF_8))
        val postID: List<String>
        if (redisTemplate.hasKey(redisKey)) {
            val obj = redisTemplate.opsForValue().get(redisKey) as String
            postID = JSONArray.parseArray(obj, String::class.java)
        } else {
            val posts = linkService.selectLinkinBeans(
                UsdUserFavoritesMapper::class,
                UsdUserFavorites::class,
                listOf(Pair(UsdUserFavorites::uid, json["user_id"] as String))
            ) ?: return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
            postID = posts.map { it.id }
            val obj = JSON.toJSONString(postID)
            redisTemplate.opsForValue().set(redisKey, obj, 20, TimeUnit.MINUTES)
        }
        query.`in`(UsdPost::id, postID)
        val pages = this.baseMapper.selectPage(page, query)
        return Result.ok(pages.records)
    }

    override fun selectAllPost(token: String, pageIndex: Long, pageItems: Long): Result {
        AppResourceConfig.forceRefreshFolder(POST_CACHE) //强制刷新redis
        val page = Page<UsdPost>(pageIndex, pageItems)
        val query = KtQueryWrapper(UsdPost::class.java)
        val redisKey = USER_ALL_IDS+":" + DigestUtils.md5DigestAsHex(token.toByteArray(Charsets.UTF_8))
        val postID: List<String>
        if (redisTemplate.hasKey(redisKey)) {
            val obj = redisTemplate.opsForValue().get(redisKey) as String
            postID = JSONArray.parseArray(obj, String::class.java)
        } else {
            val groupService = BeanContext.getBeanbyClazz(UsdGroupService::class.java)
            val groups = groupService.selectUserJoinedGroup(token)
            val groupId = (groups.data as List<UsdGroup>).map { it.id }
            postID = linkService.batchSelectLinkBeans(
                UsdGroupPostMapper::class,
                UsdGroupPost::class,
                Pair(UsdGroupPost::groupId, groupId)
            )?.map { it.postId } ?: return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
            redisTemplate.opsForValue().set(redisKey, JSON.toJSONString(postID), 20, TimeUnit.MINUTES)
        }
        query.`in`(UsdPost::id, postID)
        val pages = this.baseMapper.selectPage(page, query)
        return Result.ok(pages.records)
    }

    override fun likePost(token: String, pid: String): Result {
        val redisKey = POST_CACHE +":"+ DigestUtils.md5DigestAsHex(pid.toByteArray(Charsets.UTF_8))
        if(redisTemplate.hasKey(redisKey)){
            val obj = redisTemplate.opsForValue().get(redisKey)
            val expireTime = redisTemplate.opsForValue().operations.getExpire(redisKey)!!
            val post = JSON.parseObject(JSON.toJSONString(obj),UsdPost::class.java)
            post.likes = post.likes+1
            redisTemplate.opsForValue().set(redisKey,post,expireTime,TimeUnit.SECONDS)
        }else{
            val query = KtQueryWrapper(UsdPost::class.java)
            query.eq(UsdPost::id,pid)
            val post = this.baseMapper.selectOne(query)
            post.likes = post.likes +1
            redisTemplate.opsForValue().set(redisKey,post,1,TimeUnit.MINUTES)
        }
        return Result.ok()
    }

}