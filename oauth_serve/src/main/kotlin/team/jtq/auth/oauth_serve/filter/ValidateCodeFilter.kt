package team.jtq.auth.oauth_serve.filter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.bind.ServletRequestBindingException
import org.springframework.web.bind.ServletRequestUtils
import org.springframework.web.filter.OncePerRequestFilter
import team.jtq.auth.oauth_serve.tools.MD5Encode
import team.jtq.auth.oauth_serve.tools.handler.SecurityLoginFailureHandler
import team.jtq.auth.oauth_serve.tools.handler.ValidateCodeException
import java.io.IOException
import java.util.*
import javax.annotation.Resource
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class ValidateCodeFilter : OncePerRequestFilter() {
    @Autowired
    private val securityLoginFailureHandler: SecurityLoginFailureHandler? = null

    @Resource
    private val redisTemplate: RedisTemplate<String, String>? = null
    override  fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (request.requestURI == "/doLogin" && request.method.equals(HttpMethod.POST.name, ignoreCase = true)) {
            try {
                validate(request)
            } catch (e: ValidateCodeException) {
                securityLoginFailureHandler!!.onAuthenticationFailure(request, response, e)
                return
            }
        }
        filterChain.doFilter(request, response)
    }

    private fun validate(request: HttpServletRequest) {
        val code = ServletRequestUtils.getStringParameter(request, "code")
        val timestamp = ServletRequestUtils.getStringParameter(request, "timestamp")
        val realKey= MD5Encode(code!!.lowercase(Locale.getDefault()) + timestamp, "utf-8")
        val serverCode = redisTemplate!!.opsForValue()[realKey]
        realKey.let { redisTemplate.delete(it) }
        if (serverCode == null || !serverCode.equals(code, ignoreCase = true)) {
            throw ValidateCodeException("验证码不正确")
        }
    }
}