package team.jtq.epi_serve.entity.vo

import team.jtq.epi_serve.annotation.NoArg

@NoArg
data class UserInfoView(
    var signature:String?,
    var occupation:String?,
    var portrait:String,
    var nickName:String
)
