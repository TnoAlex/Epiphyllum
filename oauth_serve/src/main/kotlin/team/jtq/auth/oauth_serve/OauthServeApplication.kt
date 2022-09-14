package team.jtq.auth.oauth_serve

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter


@SpringBootApplication
class OauthServeApplication{
    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurerAdapter() {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .allowCredentials(false)
                    .allowedMethods("POST","GET","DELETE","PUT","OPTIONS")
                    .allowedOrigins("http://127.0.0.1:8086")
            }
        }
    }
}
fun main(args: Array<String>) {
    runApplication<OauthServeApplication>(*args)

}
