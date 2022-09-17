package team.jtq.epi_serve.entity.ao

import team.jtq.epi_serve.annotation.NoArg

@NoArg
data class UserGroupSwapEntity(
    val token:String,
    val gid:String
)
