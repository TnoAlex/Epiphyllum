package team.jtq.epi_serve.entity.vo

import team.jtq.epi_serve.annotation.NoArg

@NoArg
data class PostView(
    var pid:String,
    var createrAvatar:String,
    var context:String,
    var createTime:String,
    var createUser:String,
    var likes: Int,
    var favorites: Int,
    var comment:Int,
)
