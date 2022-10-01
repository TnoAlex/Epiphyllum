package team.jtq.epi_serve.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import team.jtq.epi_serve.entity.ao.CommentUploadEntity
import team.jtq.epi_serve.entity.ao.PostUpLoadeEntity
import team.jtq.epi_serve.service.UsdPostService
import team.jtq.epi_serve.tools.Result

@Controller
class PostController {

    @Autowired
    private lateinit var postService: UsdPostService


    //上传帖子
    @PostMapping("/usd/post/upload/{token}")
    @ResponseBody
    fun upLoadPost(@RequestBody entity: PostUpLoadeEntity,@PathVariable token: String): Result {
        return postService.addUserPost(entity,token)
    }

    //删除帖子
    @PostMapping("/usd/post/delete/{pid}/{token}")
    @ResponseBody
    fun deletePost(@PathVariable pid: String, @PathVariable token: String):Result{
        return postService.deleteUserPost(token,pid)
    }

    //收藏帖子
    @PostMapping("/usd/post/favorite/add/{pid}/{token}")
    @ResponseBody
    fun favoritePost(@PathVariable pid: String, @PathVariable token: String): Result {
        return postService.favoritePosts(token,pid)
    }

    //取消收藏
    @PostMapping("/usd/post/favorite/unfavorite/{pid}/{token}")
    @ResponseBody
    fun unfavoritePost(@PathVariable pid: String, @PathVariable token: String): Result {
        return postService.unFavoritePost(token, pid)
    }

    //查找收藏的帖子
    @GetMapping("/usd/post/favorite/all-post/{pageIndex}/{pageItems}/{token}")
    @ResponseBody
    fun selectAllFavorite(@PathVariable token: String, @PathVariable pageIndex: String, @PathVariable pageItems: String): Result {
        return postService.selectAllFavorite(token,pageIndex.toLong(),pageItems.toLong())
    }

    //查看所有帖子
    @GetMapping("/usd/post/all-post/{pageIndex}/{pageItems}/{token}")
    @ResponseBody
    fun selectAllPost(@PathVariable token: String, @PathVariable pageIndex: String, @PathVariable pageItems: String): Result {
        return postService.selectAllPost(token,pageIndex.toLong(),pageItems.toLong())
    }

    //点赞帖子
    @PostMapping("/usd/post/like-post/{pid}/{token}")
    @ResponseBody
    fun likePost(@PathVariable pid: String, @PathVariable token: String): Result {
        return postService.likePost(token,pid)
    }

    //修改帖子
    @PostMapping("/usd/post/modfiy-post/{pid}/{token}")
    @ResponseBody
    fun modfiyPost(@PathVariable pid: String, @PathVariable token: String,entity: PostUpLoadeEntity): Result {
        return postService.modfiyPost(token, pid,entity)
    }

    //发评论
    @PostMapping("/usd/post/comment/{pid}/{token}")
    @ResponseBody
    fun postComment(@PathVariable pid: String, @PathVariable token: String,@RequestBody comment:CommentUploadEntity): Result {
        return postService.postComment(token, pid,comment.content)
    }

    //显示帖子评论
    @GetMapping("/usd/post/get-comment/{pid}/{pageIndex}/{pageItems}/{token}")
    @ResponseBody
    fun selectPostComment(@PathVariable pid: String, @PathVariable token: String, @PathVariable pageIndex: String,
                          @PathVariable pageItems: String
    ): Result {
        return postService.selectPostComment(token, pid,pageIndex, pageItems)
    }

}