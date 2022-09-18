package team.jtq.epi_serve.entity.ao

import team.jtq.epi_serve.annotation.NoArg

@NoArg
data class ModifyGroupEntity (
    val gid:String,
    val token:String,
    val change:Map<String,String>
    )