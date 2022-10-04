package team.jtq.epi_serve.tools

import io.lettuce.core.output.ListOfGenericMapsOutput
import team.jtq.epi_serve.config.BeanContext
import team.jtq.epi_serve.entity.*
import team.jtq.epi_serve.entity.vo.CommentView
import team.jtq.epi_serve.entity.vo.GroupView
import team.jtq.epi_serve.entity.vo.PostView
import team.jtq.epi_serve.mapper.UsdGroupUserMapper
import team.jtq.epi_serve.mapper.UsdUserMapper
import team.jtq.epi_serve.mapper.UsdUserPostMapper
import team.jtq.epi_serve.service.MapperReflectionService
import java.time.format.DateTimeFormatter

fun adapterPostView(posts:List<UsdPost>): ArrayList<PostView>? {
    val obj = PostView::class.java
    val res = ArrayList<PostView>()
    val linkMapper = BeanContext.getBeanbyClazz(MapperReflectionService::class.java)
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
    val linkMapper = BeanContext.getBeanbyClazz(MapperReflectionService::class.java)
    val uIds = comments.associate { it.id to it.createBy }
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

fun adapterGroupView(groups:List<UsdGroup>):ArrayList<GroupView>?{
    val obj =GroupView::class.java
    val res = ArrayList<GroupView>()
    val linkService = BeanContext.getBeanbyClazz(MapperReflectionService::class.java)

    val uIds = groups.associate { it.id to it.createUser }
    val userNikeName = linkService.batchSelectLinkBeansInList(
        UsdUserMapper::class,
        UsdUser::class,
        Pair(UsdUser::uid,uIds.values.toList())
    )?.associate { it.uid to it.nickName }?:return null

    groups.forEach {
        val lastingUser = linkService.limlitOrderSelectBeansInList(
            UsdGroupUserMapper::class,
            UsdGroupUser::class,
            listOf(Pair(UsdGroupUser::groupId, listOf(it.id))),
            "DESC",
            Pair(0,10),
            UsdGroupUser::joinedTime
        )?.map { s->s.uid }?:return null
        val lastUserIco = linkService.limitSelectLinkBeansInList(
            UsdUserMapper::class,
            UsdUser::class,
            Pair(UsdUser::uid, lastingUser),
            Pair(0,10)
        )?.associate{ s -> s.portrait to s.nickName} ?:return null
        val joinedNum = linkService.countLinkBeans(
            UsdGroupUserMapper::class,
            UsdGroupUser::class,
            listOf(Pair(UsdGroupUser::groupId,it.id))
        )
        val instant = obj.newInstance()
        instant.gid = it.id
        instant.groupIco = it.groupIco
        instant.createUser = userNikeName[uIds[it.id]]!!
        instant.groupDescription =it.groupDescription
        instant.recentlyJoined = lastUserIco.keys.toList()
        instant.groupName = it.groupName
        instant.createTime  = it.createTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        instant.nickNames = lastUserIco.values.toList()
        instant.joinedNum = joinedNum?:0
        res.add(instant)
    }
    return res
}