package team.jtq.auth.oauth_serve.tools.handler

import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import team.jtq.auth.oauth_serve.entity.ao.ResultStatusCode
import team.jtq.auth.oauth_serve.tools.writeJavaScript
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import team.jtq.auth.oauth_serve.tools.Result

@Component
class OauthExceptionEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        authException.printStackTrace()
        val cause = authException.cause
        response.status = HttpStatus.OK.value()
        if (cause is InvalidTokenException) {
            writeJavaScript(response, Result.error(ResultStatusCode.INVALID_TOKEN))
        } else {
           writeJavaScript(response, Result.error(ResultStatusCode.TOKEN_MISS))
        }
    }
}