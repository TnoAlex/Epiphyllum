package team.jtq.auth.oauth_serve.entity.ao

import lombok.Data
import team.jtq.auth.oauth_serve.annotation.NoArg


@NoArg
@Data
data class RegisterEntity(
    var username:String,
    var account:String,
    var password:String,
    var phone:String,
    var identification:String,
    var gender:Int,
    var accountLevel:Int,
    var addition:String
) {
}