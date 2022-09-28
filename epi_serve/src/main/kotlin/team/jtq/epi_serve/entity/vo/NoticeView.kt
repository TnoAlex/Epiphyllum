package team.jtq.epi_serve.entity.vo

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import team.jtq.epi_serve.annotation.NoArg
import java.time.LocalDateTime

@NoArg
data class NoticeView(
    var context:String,
    var createTime:String,
    var createrPortrait:String,
    var status:Int,
    var nid:String,
    var createBy:String,
)
