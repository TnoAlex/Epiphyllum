package team.jtq.auth.oauth_serve.adapter

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.provider.ClientDetails
import team.jtq.auth.oauth_serve.entity.OauthClientDetails
import team.jtq.auth.oauth_serve.tools.getSetBySplit


class ClientDetailsAdapter(private val clientDetails: OauthClientDetails) : ClientDetails {

    override fun getClientId(): String {
        return clientDetails.appKey
    }

    override fun getResourceIds(): Set<String> {
        return getSetBySplit(clientDetails.resourceIds)
    }

    override fun isSecretRequired(): Boolean {
        return true
    }

    override fun getClientSecret(): String {
        return clientDetails.appSecret
    }

    override fun isScoped(): Boolean {
        return true
    }

    override fun getScope(): Set<String> {
        return getSetBySplit(clientDetails.scope)
    }

    override fun getAuthorizedGrantTypes(): Set<String> {
        return getSetBySplit(clientDetails.authorizedGrantTypes)
    }

    override fun getRegisteredRedirectUri(): Set<String> {
        return getSetBySplit(clientDetails.redirectUri)
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        val list: MutableList<GrantedAuthority> = ArrayList()
        for (item in getSetBySplit(clientDetails.authorities)) {
            val authority: GrantedAuthority = SimpleGrantedAuthority(item)
            list.add(authority)
        }
        return list
    }

    override fun getAccessTokenValiditySeconds(): Int {
        return clientDetails.accessTokenValidity
    }

    override fun getRefreshTokenValiditySeconds(): Int {
        return clientDetails.refreshTokenValidity
    }

    override fun isAutoApprove(scope: String): Boolean {
        return false
    }

    override fun getAdditionalInformation(): Map<String, Any> ?{
        return null
    }
    fun isTrusted(): Boolean {
        return clientDetails.trusted == 1
    }

    fun getClientDetails(): OauthClientDetails {
        return clientDetails
    }

}