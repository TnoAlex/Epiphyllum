package team.jtq.epi_serve.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import team.jtq.epi_serve.interceptor.AccessInterceptor
import team.jtq.epi_serve.interceptor.AuthorityInterceptor

@Configuration
class AppMvcConfig:WebMvcConfigurer {

    @Autowired
    private lateinit var accessInterceptor: AccessInterceptor
    @Autowired
    private lateinit var authorityInterceptor: AuthorityInterceptor

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(accessInterceptor).addPathPatterns("/**")
            .excludePathPatterns("/login/**","/code/**","/doLogin/**","/register/**","/error/**")
        registry.addInterceptor(authorityInterceptor).addPathPatterns("/race/**")
            .excludePathPatterns("/race/registration/**","/race/user/**","/usd/race/all-race/**")
    }
}