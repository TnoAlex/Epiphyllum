package team.jtq.auth.oauth_serve.entity

import lombok.Data
import team.jtq.auth.oauth_serve.annotation.NoArg


@NoArg
@Data
data class RegisterEntity(
    var username:String,
    var account:String,
    var password:String,
    var phone:String,
    var gender:Int,
    var accountLevel:Int,
    var addition:String
) {
}