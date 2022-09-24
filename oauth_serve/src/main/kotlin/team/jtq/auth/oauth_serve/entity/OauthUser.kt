package team.jtq.auth.oauth_serve.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import lombok.Data
import team.jtq.auth.oauth_serve.annotation.NoArg
import java.io.Serializable
import java.time.LocalDateTime


@Data
@TableName("oauth_user")
@NoArg
data class OauthUser(
    @TableId(type = IdType.ASSIGN_ID)
    var id: String,
    var account: String,
    var userName: String,
    var password: String,
    var phone: String?,
    var gender: Int,
    var status: Int,
    var delFlag: Int,
    var createBy: Long,
    var createTime: LocalDateTime,
    var updateBy: Long,
    var updateTime: LocalDateTime,
    var addition:String,
    var identification:String,
    @TableField(exist = false)
    var roleList: List<OauthRole>,
) : Serializable {
}