package team.jtq.auth.oauth_serve.tools.handler

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class SecurityLoginFailureHandler : AuthenticationFailureHandler {

    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationFailure(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        exception: AuthenticationException?
    ) {
        if (exception is ValidateCodeException) {
            response.sendRedirect("/login?error=codeError")
        } else {
            response.sendRedirect("/login?error=1")
        }
    }
}

class ValidateCodeException(msg: String?) : AuthenticationException(msg)