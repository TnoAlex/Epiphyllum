package team.jtq.auth.oauth_serve.filter

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CustomCORSFilter() : CorsFilter(configurationSource()) {
    companion object{
        fun configurationSource(): UrlBasedCorsConfigurationSource {
            val corsConfig = CorsConfiguration()
            val allowedHeaders = listOf("x-auth-token", "content-type", "X-Requested-With", "XMLHttpRequest")
            val exposedHeaders = listOf("x-auth-token", "content-type", "X-Requested-With", "XMLHttpRequest")
            val allowedMethods = listOf("POST", "GET", "DELETE", "PUT", "OPTIONS")
            val allowedOrigins = listOf("allowedOriginPatterns")
            corsConfig.allowedHeaders = allowedHeaders
            corsConfig.allowedMethods = allowedMethods
            corsConfig.allowedOrigins = allowedOrigins
            corsConfig.exposedHeaders = exposedHeaders
            corsConfig.maxAge = 36000L
            corsConfig.allowCredentials = true

            val source = UrlBasedCorsConfigurationSource()
            source.registerCorsConfiguration("/**", corsConfig)
            return source
        }

    }
}