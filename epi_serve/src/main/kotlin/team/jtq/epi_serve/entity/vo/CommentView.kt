package team.jtq.epi_serve.entity.vo

import team.jtq.epi_serve.annotation.NoArg

@NoArg
data class CommentView(
    var commentContext:String,
    var createTime:String,
    var createBy:String,
    var createRavatar:String,
    var createId:String,
    var cid:String
)
