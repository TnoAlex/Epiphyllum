package team.jtq.auth.oauth_serve.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import lombok.Data
import team.jtq.auth.oauth_serve.annotation.NoArg
import java.io.Serializable
import java.time.LocalDateTime


@Data
@TableName("oauth_role")
@NoArg
data class OauthRole(
    @TableId(type = IdType.ASSIGN_ID)
    var id: String,
    var roleCode: String,
    var roleName: String,
    var createBy: Long,
    var createTime: LocalDateTime,
    var updateBy: Long,
    var updateTime: LocalDateTime,
    var canExudes:Int,

    ) : Serializable {
    companion object {
        private const val serialVersionUID = 1275468202514560808L
    }
}