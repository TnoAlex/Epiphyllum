package team.jtq.epi_serve.service

import com.baomidou.mybatisplus.extension.service.IService
import team.jtq.epi_serve.entity.UsdPost
import team.jtq.epi_serve.entity.ao.PostUpLoadeEntity
import team.jtq.epi_serve.tools.Result

interface UsdPostService : IService<UsdPost> {

    fun addUserPost(entity: PostUpLoadeEntity, token: String): Result
    fun deleteUserPost(token: String, pid: String): Result
    fun favoritePosts(token: String, pid: String): Result
    fun unFavoritePost(token: String, pid: String): Result
    fun selectAllFavorite(token: String, pageIndex: Long, pageItems: Long): Result
    fun selectAllPost(token: String, pageIndex: Long, pageItems: Long): Result
    fun likePost(token: String, pid: String): Result
    fun modfiyPost(token: String, pid: String, entity: PostUpLoadeEntity): Result
    fun postComment(token: String, pid: String, comment: String): Result
    fun selectPostComment(token: String, pid: String, pageIndex: String, pageItems: String): Result
}