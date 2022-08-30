package team.jtq.auth.oauth_serve.config

import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.OAuth2RequestFactory
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory
import org.springframework.security.oauth2.provider.token.TokenStore
import team.jtq.auth.oauth_serve.service.Imp.OauthClientDetailsServiceImp
import team.jtq.auth.oauth_serve.service.Imp.OauthCodeServiceImp
import team.jtq.auth.oauth_serve.service.Imp.OauthUserDetailServiceImp
import team.jtq.auth.oauth_serve.tools.handler.*


@Slf4j
@Configuration
class OAuthServerConfiguration {
    private val RESOURCE_ID = "USER-RESOURCE"

    /**
     * 资源服务器配置
     */
    @Configuration
    @EnableResourceServer
    protected inner class ApiResourceServerConfiguration : ResourceServerConfigurerAdapter() {
        @Autowired
        private lateinit var oauthTokenExtractor: OauthTokenExtractor

        @Autowired
        private lateinit var oauthExceptionEntryPoint: OauthExceptionEntryPoint

        @Autowired
        private lateinit var oauthAccessDeniedHandler: OauthAccessDeniedHandler

        override fun configure(resources: ResourceServerSecurityConfigurer) {
            resources.resourceId(RESOURCE_ID).stateless(false)
            // token提取器
            resources.tokenExtractor(oauthTokenExtractor) // token异常处理器
                .authenticationEntryPoint(oauthExceptionEntryPoint) // 无权限异常处理器
                .accessDeniedHandler(oauthAccessDeniedHandler)
        }

        override fun configure(http: HttpSecurity) {
            http // STATELESS表示一定要携带access_token才能访问，无法通过session访问
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .requestMatchers().antMatchers("/get/**")
                .and()
                .authorizeRequests()
                .antMatchers("/get/**").access("#oauth2.hasScope('read')")
        }
    }


    @Configuration
    @EnableResourceServer
    protected inner class SyncResourceServerConfiguration : ResourceServerConfigurerAdapter() {
        @Autowired
        private lateinit var oauthTokenExtractor: OauthTokenExtractor

        @Autowired
        private lateinit var oauthExceptionEntryPoint: OauthExceptionEntryPoint

        @Autowired
        private lateinit var oauthAccessDeniedHandler: OauthAccessDeniedHandler

        override fun configure(resources: ResourceServerSecurityConfigurer) {
            resources.resourceId(RESOURCE_ID).stateless(false)
            // token提取器
            resources.tokenExtractor(oauthTokenExtractor) // token异常处理器
                .authenticationEntryPoint(oauthExceptionEntryPoint) // 无权限异常处理器
                .accessDeniedHandler(oauthAccessDeniedHandler)
        }

        override fun configure(http: HttpSecurity) {
            http // STATELESS表示一定要携带access_token才能访问，无法通过session访问
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .requestMatchers().antMatchers("/update/**")
                .and()
                .authorizeRequests()
                .antMatchers("/update/**").access("#oauth2.hasScope('write') && hasRole('admin')")
        }
    }

    @Configuration
    @EnableAuthorizationServer
    inner class AuthorizationServerConfiguration : AuthorizationServerConfigurerAdapter() {

        @Autowired
        private lateinit var tokenStore: TokenStore

        @Autowired
        private lateinit var oauthClientDetailsService: OauthClientDetailsServiceImp

        @Autowired
        private lateinit var authorizationCodeServices: OauthCodeServiceImp

        @Autowired
        private lateinit var userDetailService: OauthUserDetailServiceImp

        @Autowired
        @Qualifier("authenticationManagerBean")
        private lateinit var authenticationManager: AuthenticationManager

        @Autowired
        private lateinit var oauthWebResponseExceptionTranslator: OauthWebResponseExceptionTranslator

        override fun configure(clients: ClientDetailsServiceConfigurer) {
            clients.withClientDetails(oauthClientDetailsService)
        }


        override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
            endpoints.tokenStore(tokenStore)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST) // 自定义认证异常处理
                .exceptionTranslator(oauthWebResponseExceptionTranslator) // 自定义的授权码模式的code（授权码）处理，使用redis存储
                .authorizationCodeServices(authorizationCodeServices) // 用户信息service
                .userDetailsService(userDetailService) // 用户授权确认处理器
                .userApprovalHandler(userApprovalHandler()) // 注入authenticationManager来支持password模式
                .authenticationManager(authenticationManager) // 自定义授权确认页面
                .pathMapping("/oauth/confirm_access", "/approval")
        }


        override fun configure(oauthServer: AuthorizationServerSecurityConfigurer) {
            // 允许 /oauth/token的端点表单认证
            oauthServer.allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()") // 允许 /oauth/token_check端点的访问
                .checkTokenAccess("permitAll()")
        }

        @Bean
        fun oAuth2RequestFactory(): OAuth2RequestFactory {
            return DefaultOAuth2RequestFactory(oauthClientDetailsService)
        }

        @Bean
        fun userApprovalHandler(): UserApprovalHandler {
            val userApprovalHandler = OauthUserApprovalHandler()
            userApprovalHandler.setTokenStore(tokenStore)
            userApprovalHandler.setClientDetailsService(oauthClientDetailsService)
            userApprovalHandler.setRequestFactory(oAuth2RequestFactory())
            return userApprovalHandler
        }
    }
}