package team.jtq.auth.oauth_serve.tools.handler

import lombok.extern.slf4j.Slf4j
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails
import org.springframework.security.oauth2.provider.authentication.TokenExtractor
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest


@Slf4j
@Component
class OauthTokenExtractor : TokenExtractor {
    override fun extract(request: HttpServletRequest): PreAuthenticatedAuthenticationToken? {
        val tokenValue = extractToken(request)
        return if (tokenValue != null) {
            PreAuthenticatedAuthenticationToken(tokenValue, "")
        } else {
            null
        }
    }

    fun extractToken(request: HttpServletRequest): String? {
        var token = extractHeaderToken(request)
        if (token == null) {
            token = request.getParameter(OAuth2AccessToken.ACCESS_TOKEN)
            if (token == null) {
                request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE, OAuth2AccessToken.BEARER_TYPE)
                token = extractCookieToken(request)
                if (token != null) {
                    request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE, "Bearer")
                }
            }
        }
        return token
    }

    private fun extractHeaderToken(request: HttpServletRequest): String? {
        val headers = request.getHeaders("Authorization")
        while (headers.hasMoreElements()) { // typically there is only one (most servers enforce that)
            val value = headers.nextElement()
            if (value.lowercase(Locale.getDefault()).startsWith(OAuth2AccessToken.BEARER_TYPE.toLowerCase())) {
                var authHeaderValue: String = value.substring(OAuth2AccessToken.BEARER_TYPE.length).trim { it <= ' ' }
                // Add this here for the auth details later. Would be better to change the signature of this method.
                request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE,
                    value.substring(0, OAuth2AccessToken.BEARER_TYPE.length).trim { it <= ' ' })
                val commaIndex = authHeaderValue.indexOf(',')
                if (commaIndex > 0) {
                    authHeaderValue = authHeaderValue.substring(0, commaIndex)
                }
                return authHeaderValue
            }
        }
        return null
    }

    private fun extractCookieToken(request: HttpServletRequest): String? {
        var cookieToken: String? = null
        val cookies: Array<Cookie>? = request.cookies //根据请求数据，找到cookie数组
        if (null != cookies && cookies.isNotEmpty()) {
            for (cookie in cookies) {
                if (null != cookie.name && cookie.name.trim().equals("access_token",ignoreCase = true)) {
                    cookieToken = cookie.value.trim()
                    break
                }
            }
        }
        return cookieToken
    }
}