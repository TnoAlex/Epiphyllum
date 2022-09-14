package team.jtq.auth.oauth_serve.service

import com.baomidou.mybatisplus.extension.service.IService
import org.springframework.security.oauth2.provider.ClientDetailsService
import team.jtq.auth.oauth_serve.entity.OauthAppRegister
import team.jtq.auth.oauth_serve.entity.OauthClientDetails
import team.jtq.auth.oauth_serve.tools.Result

interface OauthClientDetailsService:IService<OauthClientDetails>, ClientDetailsService {
    fun registApp(entity:OauthAppRegister):Result
}