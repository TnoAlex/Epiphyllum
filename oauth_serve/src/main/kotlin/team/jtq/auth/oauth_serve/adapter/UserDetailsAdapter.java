package team.jtq.auth.oauth_serve.adapter;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import team.jtq.auth.oauth_serve.entity.OauthRole;
import team.jtq.auth.oauth_serve.entity.OauthUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Setter
public class UserDetailsAdapter implements UserDetails {

    private String username;
    private String password;
    /**
     * 权限
     */
    private List<GrantedAuthority> authorities;
    private boolean enable;
    private static final String defaultRolePrefix = "ROLE_";

    public UserDetailsAdapter(){

    }

    public UserDetailsAdapter(OauthUser oauthUser) {
        this.username = oauthUser.getAccount();
        this.password = oauthUser.getPassword();

        List<GrantedAuthority> list = new ArrayList<>();
        if(!oauthUser.getRoleList().isEmpty()){
            for (OauthRole role : oauthUser.getRoleList()) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(defaultRolePrefix + role.getRoleCode());
                list.add(grantedAuthority);
            }
        }
        this.authorities = list;
        this.enable = (oauthUser.getDelFlag() == 0 && oauthUser.getStatus() == 0);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}