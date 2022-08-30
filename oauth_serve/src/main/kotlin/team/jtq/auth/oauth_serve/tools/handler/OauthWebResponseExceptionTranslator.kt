package team.jtq.auth.oauth_serve.tools.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator
import org.springframework.stereotype.Component

@Component
class OauthWebResponseExceptionTranslator : WebResponseExceptionTranslator<OAuth2Exception> {
    override fun translate(e: Exception): ResponseEntity<OAuth2Exception> {
        e.printStackTrace()
        val oauth = e as OAuth2Exception
        return  ResponseEntity<OAuth2Exception>(oauth, HttpStatus.OK)
    }
}