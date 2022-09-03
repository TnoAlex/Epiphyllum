package team.jtq.auth.oauth_serve.filter

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CustomCORSFilter() : Filter {
    override fun doFilter(servletRequest : ServletRequest, servletResponse : ServletResponse, filterChain: FilterChain) {
        val request= servletRequest as HttpServletRequest
        val response= servletResponse as HttpServletResponse
        response.setHeader("Access-Control-Allow-Origin", "*")
        response.setHeader("Access-Control-Allow-Credentials", "true")
        response.setHeader("Access-Control-Allow-Methods", "POST,GET")
        response.setHeader("Access-Control-Allow-Max-Age", "3600")
        response.setHeader("Access-Control-Allow-Headers", "*")
        if ("OPTIONS".equals(request.getMethod(), ignoreCase = true)) {
            response.status = HttpServletResponse.SC_OK
        } else {
            filterChain.doFilter(servletRequest, servletResponse)
        }
    }
}