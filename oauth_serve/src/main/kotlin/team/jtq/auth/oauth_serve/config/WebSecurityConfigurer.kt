package team.jtq.auth.oauth_serve.config

import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter
import team.jtq.auth.oauth_serve.filter.CustomCORSFilter
import team.jtq.auth.oauth_serve.filter.ValidateCodeFilter
import team.jtq.auth.oauth_serve.service.Imp.OauthUserDetailServiceImp
import team.jtq.auth.oauth_serve.tools.handler.SecurityLoginFailureHandler


@Slf4j
@Configuration
@EnableWebSecurity
class WebSecurityConfigurer : WebSecurityConfigurerAdapter() {
    @Autowired
    private lateinit var userDetailService: OauthUserDetailServiceImp

    @Autowired
    private lateinit var validateCodeFilter: ValidateCodeFilter

    @Autowired
    private lateinit var securityLoginFailureHandler: SecurityLoginFailureHandler

    @Autowired
    private lateinit var corsFilter: CustomCORSFilter

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(web: WebSecurity) {
        //Ignore, public
        web.ignoring().antMatchers("/public/**", "/static/**")
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        http.cors()
        http.authorizeRequests()
            .antMatchers("/code/**").permitAll()
            .antMatchers("/antd/**", "/vue/**", "/img/**").permitAll()
            .antMatchers("/oauth/rest_token*").permitAll()
            .antMatchers("/doLogin").permitAll()
            .antMatchers("/login*").permitAll()
            .antMatchers(HttpMethod.GET, "/login*").anonymous()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/doLogin")
            .defaultSuccessUrl("/index") // 登录失败异常处理
            .failureHandler(securityLoginFailureHandler) //.failureUrl("/login?error=1")
            .usernameParameter("username")
            .passwordParameter("password")
            .and()
            .logout()
            .logoutUrl("/logout")
            .deleteCookies("JSESSIONID")
            .logoutSuccessUrl("/login")
            .and()
            .exceptionHandling()
            .and() //验证码过滤器
            .addFilterBefore(corsFilter,WebAsyncManagerIntegrationFilter::class.java)
            .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter::class.java)
            .authenticationProvider(authenticationProvider())
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val daoAuthenticationProvider = DaoAuthenticationProvider()
        daoAuthenticationProvider.setUserDetailsService(userDetailService)
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder())
        return daoAuthenticationProvider
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}