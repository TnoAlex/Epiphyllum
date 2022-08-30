package team.jtq.auth.oauth_serve.entity

import lombok.Data
import team.jtq.auth.oauth_serve.annotation.NoArg
import java.io.Serializable


@Data
@NoArg
class OauthCode  (
    var code: String,
    var authentication: ByteArray

): Serializable{
    companion object {
        private const val serialVersionUID = -1326285297837903604L
    }
}