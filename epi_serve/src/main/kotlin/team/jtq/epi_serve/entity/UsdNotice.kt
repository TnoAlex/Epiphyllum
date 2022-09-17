package team.jtq.epi_serve.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import team.jtq.epi_serve.annotation.NoArg
import java.time.LocalDateTime

@NoArg
@TableName("usd_notice")
data class UsdNotice(
    @TableId(type = IdType.ASSIGN_ID)
    var id:String,
    var context:String,
    var status:Int,
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    var createTime: LocalDateTime
)
