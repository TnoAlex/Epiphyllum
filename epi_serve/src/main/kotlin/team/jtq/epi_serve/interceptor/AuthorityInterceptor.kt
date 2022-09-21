package team.jtq.epi_serve.interceptor

import com.alibaba.fastjson2.JSON
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import team.jtq.epi_serve.entity.ao.ResultStatusCode
import team.jtq.epi_serve.service.TokenService
import team.jtq.epi_serve.tools.returnJson
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthorityInterceptor: HandlerInterceptor {

    @Autowired
    private lateinit var tokenService: TokenService


    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token = request.getParameter("token")
        val json = tokenService.getUserInfo(token)!!
        if(json["authorities"] as String == "ROLE_user"){
            returnJson(response,"权限不足","403")
            return false
        }
        return true
    }


}