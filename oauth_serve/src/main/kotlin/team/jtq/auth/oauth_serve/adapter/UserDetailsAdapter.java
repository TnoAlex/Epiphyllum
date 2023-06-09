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
    private String phone;
    private String nickname;
    private Integer status;
    private String gender;

    private String identification;

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    private String addition;

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public String getNickname() {
        return nickname;
    }

    public Integer getStatus() {
        return status;
    }

    public String getGender() {
        return gender;
    }

    /**
     * 权限
     */
    private List<GrantedAuthority> authorities;
    private boolean enable;
    private static final String defaultRolePrefix = "ROLE_";

    public UserDetailsAdapter() {

    }

    public UserDetailsAdapter(OauthUser oauthUser) {
        this.username = oauthUser.getAccount();
        this.password = oauthUser.getPassword();
        this.nickname = oauthUser.getUserName();
        this.phone = oauthUser.getPhone();
        this.status = oauthUser.getStatus();
        this.id = oauthUser.getId();
        this.addition = oauthUser.getAddition();
        this.gender = oauthUser.getGender() == 0 ? "女" : "男";
        this.identification = oauthUser.getIdentification();

        List<GrantedAuthority> list = new ArrayList<>();
        if (!oauthUser.getRoleList().isEmpty()) {
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