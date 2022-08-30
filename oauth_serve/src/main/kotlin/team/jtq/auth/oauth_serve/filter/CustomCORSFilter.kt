package team.jtq.auth.oauth_serve.filter

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CustomCORSFilter() : CorsFilter(configurationSource()) {
    companion object{
        fun configurationSource():CorsConfigurationSource{
            val config = CorsConfiguration()
            val allowedHeaders = listOf("x-auth-token", "content-type", "X-Requested-With", "XMLHttpRequest","Access-Control-Allow-Origin","Authorization","authorization")
            val exposedHeaders = listOf("x-auth-token", "content-type", "X-Requested-With", "XMLHttpRequest","Access-Control-Allow-Origin","Authorization","authorization")
            val allowedMethods = listOf("POST", "GET", "DELETE", "PUT", "OPTIONS")
            val allowedOrigins = listOf("*")
            config.allowedHeaders = allowedHeaders
            config.exposedHeaders = exposedHeaders
            config.allowedMethods = allowedMethods
            config.allowedOrigins = allowedOrigins
            config.maxAge = 36000L
            config.allowCredentials = true
            val source = UrlBasedCorsConfigurationSource()
            source.registerCorsConfiguration("/**",config)
            return source
        }
    }
}