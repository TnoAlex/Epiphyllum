package team.jtq.auth.oauth_serve.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import java.io.Serializable
import java.time.LocalDateTime
import lombok.Data
import team.jtq.auth.oauth_serve.annotation.NoArg

@TableName("oauth_client_details")
@Data
@NoArg
data class OauthClientDetails(
    @TableId(type = IdType.ASSIGN_ID)
    private var id: String,
    var appName: String,
    var appKey: String,
    var appSecret: String,
    var resourceIds: String,
    var scope: String,
    var authorizedGrantTypes: String,
    var redirectUri: String,
    var authorities: String,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    var accessTokenValidity: Int,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    var refreshTokenValidity: Int,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    var additionalInformation: String,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    var archived: Int,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    var trusted: Int,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    var autoapprove: String,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    var createBy: String,
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    var createTime: LocalDateTime,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    var updateBy: String,
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    var updateTime: LocalDateTime
): Serializable