package team.jtq.auth.oauth_serve.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "deploy")
object OAuthServerConfig {
    lateinit var domainName:String
    lateinit var minimumCheckLevel:String
    lateinit var maxConfirmCodeLive:String
}