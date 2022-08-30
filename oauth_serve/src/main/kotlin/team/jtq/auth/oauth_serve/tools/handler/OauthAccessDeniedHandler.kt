package team.jtq.auth.oauth_serve.tools.handler

import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import team.jtq.auth.oauth_serve.entity.ResultStatusCode
import team.jtq.auth.oauth_serve.tools.writeJavaScript
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import team.jtq.auth.oauth_serve.tools.Result

@Component
class OauthAccessDeniedHandler : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        accessDeniedException.printStackTrace()
        response.status = HttpStatus.OK.value()
        writeJavaScript(response, Result.error(ResultStatusCode.PERMISSION_DENIED))
    }

}