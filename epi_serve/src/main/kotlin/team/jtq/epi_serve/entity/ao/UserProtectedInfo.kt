package team.jtq.epi_serve.entity.ao

import team.jtq.epi_serve.annotation.NoArg

@NoArg
data class UserProtectedInfo(
    val userPhone:String,
    val userAccout:String,
    val userGender:String,
    val identification:String,
)
