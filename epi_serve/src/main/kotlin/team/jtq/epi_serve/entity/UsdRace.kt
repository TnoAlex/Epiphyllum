package team.jtq.epi_serve.entity

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import team.jtq.epi_serve.annotation.NoArg
import java.time.LocalDateTime

@NoArg
@TableName( "usd_race")
data class UsdRace (
    @TableId
    var Id: String,
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    var raceStartTime: LocalDateTime,
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    var raceEndTime: LocalDateTime,
    var raceDescription: String,
    var raceAddition: String,
    var status: Int,
    var organizer:String,
    var raceName:String
)

