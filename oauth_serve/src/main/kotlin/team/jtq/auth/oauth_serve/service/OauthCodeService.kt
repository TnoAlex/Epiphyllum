package team.jtq.auth.oauth_serve.service

import com.baomidou.mybatisplus.extension.service.IService
import org.springframework.security.oauth2.provider.OAuth2Authentication
import team.jtq.auth.oauth_serve.entity.OauthCode

interface OauthCodeService : IService<OauthCode> {
    fun store(code: String, authentication: OAuth2Authentication)
    fun remove(code: String): OAuth2Authentication?
}