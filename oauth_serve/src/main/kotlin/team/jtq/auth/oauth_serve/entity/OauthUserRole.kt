package team.jtq.auth.oauth_serve.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import lombok.Data
import team.jtq.auth.oauth_serve.annotation.NoArg
import java.io.Serializable


@Data
@TableName("oauth_user_role")
@NoArg
class OauthUserRole(
    @TableId(type = IdType.ASSIGN_ID)
    var id: String,
    var userId: String,
    var roleId: String,

    ) : Serializable {
    companion object {
        private const val serialVersionUID = 2489654110281415444L
    }
}