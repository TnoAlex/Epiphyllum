package team.jtq.epi_serve.entity.ao

import team.jtq.epi_serve.annotation.NoArg

@NoArg
data class ModifyUserEntity(
    val signature:String?,
    val occupation:String?,
    val portrait:String?,
    val nickName:String?
)
