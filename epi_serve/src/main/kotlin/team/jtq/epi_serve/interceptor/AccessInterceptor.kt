package team.jtq.epi_serve.interceptor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import team.jtq.epi_serve.entity.ao.ResultStatusCode
import team.jtq.epi_serve.service.TokenService
import team.jtq.epi_serve.tools.returnJson
import java.io.PrintWriter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class AccessInterceptor: HandlerInterceptor {

    @Autowired
    private lateinit var tokenService: TokenService

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token = request.getParameter("token")
        if(token == null){
            returnJson(response,"Token缺失","401")
            return false
        }
        val res = tokenService.checkToken(token)
        if(res.code != ResultStatusCode.OK.code)
        {
            returnJson(response,"无效的Token","403")
            return false
        }
        return true
    }

}