package team.jtq.auth.oauth_serve.entity

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import lombok.Data
import team.jtq.auth.oauth_serve.annotation.NoArg

@Data
@NoArg
@TableName("oauth_user_client")
data class OauthUserClient(
    @TableId
    var id:String,
    var clientId:String,
    var uid:String
)