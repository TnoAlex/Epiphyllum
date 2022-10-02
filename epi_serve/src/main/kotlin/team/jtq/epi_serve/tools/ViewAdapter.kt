package team.jtq.epi_serve.tools

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import org.springframework.data.redis.core.RedisTemplate
import team.jtq.epi_serve.config.BeanContext
import team.jtq.epi_serve.entity.UsdComment
import team.jtq.epi_serve.entity.UsdPost
import team.jtq.epi_serve.entity.UsdUser
import team.jtq.epi_serve.entity.UsdUserPost
import team.jtq.epi_serve.entity.vo.CommentView
import team.jtq.epi_serve.entity.vo.PostView
import team.jtq.epi_serve.mapper.UsdUserMapper
import team.jtq.epi_serve.mapper.UsdUserPostMapper
import team.jtq.epi_serve.service.UsdLinkService
import java.time.format.DateTimeFormatter

fun adapterPostView(posts:List<UsdPost>): ArrayList<PostView>? {
    val obj = PostView::class.java
    val res = ArrayList<PostView>()
    val linkMapper = BeanContext.getBeanbyClazz(UsdLinkService::class.java)
    val userPost = linkMapper.batchSelectLinkBeansInList(
        UsdUserPostMapper::class,
        UsdUserPost::class,
        Pair(UsdUserPost::postId, posts.map { it.id })
    )?.associate { it.postId to it.uid } ?:return null
    val userInfo = linkMapper.batchSelectLinkBeansInList(
        UsdUserMapper::class,
        UsdUser::class,
        Pair(UsdUser::uid, userPost.values.toList())
    )?.associate { it.uid to Pair(it.portrait, it.nickName) }?:return null
    posts.forEach {
        val instant = obj.newInstance()
        instant.createTime = it.createTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        instant.context = it.postContent
        instant.likes = it.likes
        instant.favorites = it.favorites
        instant.pid  =  it.id
        instant.createrAvatar = userInfo[userPost[it.id]!!]!!.first
        instant.createUser = userInfo[userPost[it.id]!!]!!.second
        res.add(instant)
    }
    return res
}

fun adapterCommentView(comments:List<UsdComment>): ArrayList<CommentView>? {
    val obj = CommentView::class.java
    val res = ArrayList<CommentView>()
    val linkMapper = BeanContext.getBeanbyClazz(UsdLinkService::class.java)
    val uIds = comments.associate { it.id to it.createBy }?:return null
    val userInfo = linkMapper.batchSelectLinkBeansInList(
        UsdUserMapper::class,
        UsdUser::class,
        Pair(UsdUser::uid,uIds.values.toList())
    )?.associate { it.uid to Pair(it.portrait, it.nickName) }?:return null
    comments.forEach {
        val instant = obj.newInstance()
        instant.commentContext = it.comment
        instant.cid = it.id
        instant.createTime = it.createTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        instant.createId = uIds[it.id]!!
        instant.createBy = userInfo[uIds[it.id]]!!.second
        instant.createRavatar = userInfo[uIds[it.id]]!!.first
        res.add(instant)
    }
    return res
}