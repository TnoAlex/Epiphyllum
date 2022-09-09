package team.jtq.auth.oauth_serve.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "deploy")
object AppResourceConfig {
    lateinit var domainName:String
    lateinit var minimumCheckLevel:String
}