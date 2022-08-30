package team.jtq.auth.oauth_serve.adapter

import lombok.Setter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import team.jtq.auth.oauth_serve.entity.OauthUser


@Setter
class UserDetailsAdapter : UserDetails {

    private val defaultRolePrefix = "ROLE_"
    private lateinit var username: String
    private lateinit var password: String

    private lateinit var authorities: List<GrantedAuthority>
    private var enable = false

    constructor() {}
    constructor(oauthUser: OauthUser) {
        username = oauthUser.account
        password = oauthUser.password
        val list: MutableList<GrantedAuthority> = ArrayList()
        if (!oauthUser.roleList.isEmpty()) {
            for (role in oauthUser.roleList)
                list.add(SimpleGrantedAuthority(defaultRolePrefix + role.roleCode))
        }
        authorities = list
        enable = oauthUser.delFlag == 0 && oauthUser.status == 0
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return enable
    }

}