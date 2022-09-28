package team.jtq.epi_serve.entity.ao

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
    val identification:String,
    var accountLevel:Int?,
    var addition:String?
) {
}