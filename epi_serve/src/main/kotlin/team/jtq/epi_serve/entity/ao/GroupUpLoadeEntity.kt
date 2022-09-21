package team.jtq.epi_serve.entity.ao

import team.jtq.epi_serve.annotation.NoArg

@NoArg
data class GroupUpLoadeEntity(
    var groupName: String,
    var groupDescription: String,
    var groupIco: String,
)
