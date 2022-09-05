package team.jtq.epi_serve.entity

import lombok.Data
import team.jtq.epi_serve.annotation.NoArg

@NoArg
@Data
data class LoginEntity(
    val username: String,
    val password: String,
    val code: String,
    val timestamp: String,
) {
}