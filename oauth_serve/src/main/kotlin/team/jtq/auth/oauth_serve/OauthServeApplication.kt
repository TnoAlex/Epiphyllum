package team.jtq.auth.oauth_serve

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OauthServeApplication

fun main(args: Array<String>) {
    runApplication<OauthServeApplication>(*args)
}
