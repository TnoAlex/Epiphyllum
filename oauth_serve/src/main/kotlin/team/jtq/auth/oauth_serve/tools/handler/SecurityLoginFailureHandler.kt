package team.jtq.auth.oauth_serve.tools.handler

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component
import team.jtq.auth.oauth_serve.entity.ResultStatusCode
import team.jtq.auth.oauth_serve.tools.Result
import team.jtq.auth.oauth_serve.tools.writeJavaScript
import java.io.IOException
import java.io.PrintWriter
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
            writeJavaScript(response, Result.error(ResultStatusCode.VERIFICATIONCODE_ERROR))
//            response.sendRedirect("/login?error=codeError")
        } else {
            writeJavaScript(response, Result.error(ResultStatusCode.UNKONW_ERROR))
//            response.sendRedirect("/login?error=1")
        }
    }
}

class ValidateCodeException(msg: String?) : AuthenticationException(msg)