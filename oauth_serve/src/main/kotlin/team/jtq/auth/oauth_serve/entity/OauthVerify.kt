package team.jtq.auth.oauth_serve.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import lombok.Data
import team.jtq.auth.oauth_serve.annotation.NoArg
import java.time.LocalDateTime

@Data
@NoArg
@TableName("oauth_verify")
data class OauthVerify(
    @TableId(type=IdType.ASSIGN_ID)
    var id:String,
    var requestId:String,
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    var createTime:LocalDateTime,
    var status:Int,
    var passUser:String,
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    var passTime:LocalDateTime,
    /*
    {
       credit_code:"xxxxxxx"
       other:"base64Image"
    }
    one of this is nullable
    */
    var addition:String
)
