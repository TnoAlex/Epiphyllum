package team.jtq.auth.oauth_serve.tools.handler

import lombok.extern.slf4j.Slf4j
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.provider.AuthorizationRequest
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler
import team.jtq.auth.oauth_serve.adapter.ClientDetailsAdapter
import team.jtq.auth.oauth_serve.service.Imp.OauthClientDetailsServiceImp
import team.jtq.auth.oauth_serve.tools.SpringContextUtils


@Slf4j
class OauthUserApprovalHandler : TokenStoreUserApprovalHandler() {

    override fun isApproved(authorizationRequest: AuthorizationRequest, userAuthentication: Authentication): Boolean {
        if (super.isApproved(authorizationRequest, userAuthentication)) {
            return true
        }
        if (!userAuthentication.isAuthenticated) {
            return false
        }
        val oauthClientDetailsService: OauthClientDetailsServiceImp = SpringContextUtils.getBean(
            "oauthClientDetailsService",
            OauthClientDetailsServiceImp::class.java
        )
        val clientDetails = oauthClientDetailsService.loadClientByClientId(authorizationRequest.getClientId()) as ClientDetailsAdapter?
        return clientDetails != null && clientDetails.isTrusted()
    }
}