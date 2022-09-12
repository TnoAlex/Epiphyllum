package team.jtq.auth.oauth_serve.adapter

import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter

class UserAuthenticationConverter: DefaultUserAuthenticationConverter() {

    override fun convertUserAuthentication(authentication: Authentication): MutableMap<String, *> {
        val response = LinkedHashMap<String,Any>()
        val principal = authentication.principal as UserDetailsAdapter
        response["user_name"] = principal.nickname
        response["user_account"] = principal.username
        response["user_gender"] = principal.gender
        response["user_phone"] = principal.phone
        response["user_status"] = principal.status
        if (authentication.authorities != null && !authentication.authorities.isEmpty()) {
            response[AUTHORITIES] = AuthorityUtils.authorityListToSet(authentication.authorities)
        }
        return response
    }
}