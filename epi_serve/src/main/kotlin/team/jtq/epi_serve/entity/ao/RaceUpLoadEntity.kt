package team.jtq.epi_serve.entity.ao

import team.jtq.epi_serve.annotation.NoArg

@NoArg
data class RaceUpLoadEntity(
    val startTime:String,
    val endTime:String,
    val raceDescription:String,
    var raceAddition: String,
    var raceName:String
)