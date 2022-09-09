package team.jtq.epi_serve.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


@Component
@ConfigurationProperties(prefix = "authorization-server")
object AppResourceConfig {
    lateinit var appResourcesId: String
    lateinit var appQuerySecret: String
    lateinit var oauthService: String
    lateinit var queryTokenUri: String
}
