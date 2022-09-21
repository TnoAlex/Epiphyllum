package team.jtq.epi_serve.entity.ao

import team.jtq.epi_serve.annotation.NoArg

@NoArg
data class PostUpLoadeEntity(
    val groupId:String,
    val connect:String,
)
