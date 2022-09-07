package team.jtq.epi_serve.entity

import lombok.Data
import team.jtq.epi_serve.annotation.NoArg

@NoArg
@Data
data class RegisterEntity(
    var username:String,
    var account:String,
    var password:String,
    var phone:String,
    var gender:Int,
    var accountType:String,
    var addition:String
) {
}