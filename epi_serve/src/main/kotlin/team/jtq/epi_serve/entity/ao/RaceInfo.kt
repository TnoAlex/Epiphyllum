package team.jtq.epi_serve.entity.ao

import team.jtq.epi_serve.annotation.NoArg

@NoArg
data class RaceInfo(
    val participantsNumber: Int,
    val committersNumber: Int,
)
