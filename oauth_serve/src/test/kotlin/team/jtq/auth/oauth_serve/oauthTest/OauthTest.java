package team.jtq.auth.oauth_serve.oauthTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import team.jtq.auth.oauth_serve.entity.OauthRole;
import team.jtq.auth.oauth_serve.entity.OauthUser;
import team.jtq.auth.oauth_serve.entity.OauthUserRole;
import team.jtq.auth.oauth_serve.service.Imp.OauthClientDetailsServiceImp;
import team.jtq.auth.oauth_serve.service.Imp.OauthRoleServiceImp;
import team.jtq.auth.oauth_serve.service.Imp.OauthUserDetailServiceImp;
import team.jtq.auth.oauth_serve.service.Imp.OauthUserRoleServiceImp;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OauthTest {

    @Resource
    private OauthUserDetailServiceImp userDetailService;
    @Resource
    private OauthRoleServiceImp roleService;
    @Resource
    private OauthUserRoleServiceImp userRoleService;
    @Resource
    private OauthClientDetailsServiceImp oauthClientDetailsService;

    /**
     * 添加用户和角色
     */
    @Test
    public void createUserAndRole(){
        OauthRole role = new OauthRole();
        role.setRoleCode("Testadmin");
        role.setRoleName("超级管理员");
        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());

        roleService.save(role);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        OauthUser user = new OauthUser();
        user.setAccount("Testadmin");
        user.setUserName("超级管理员");
        user.setPassword(bCryptPasswordEncoder.encode("12345"));
        user.setPhone("13866666666");
        user.setGender(1);
        user.setStatus(0);
        user.setDelFlag(0);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userDetailService.save(user);


        OauthUserRole userRole = new OauthUserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(role.getId());
        userRoleService.save(userRole);
    }
    @Test
    public void  testEncode(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String s1 = bCryptPasswordEncoder.encode("12345");
        boolean res = bCryptPasswordEncoder.matches("12345",s1);
        System.out.println(res);
    }


//    @Test
//    public void createClientDetails(){
//        OauthClientDetails details = new OauthClientDetails();
//        details.setAppName("微信");
//        details.setAppKey(String.valueOf(new Sequence().nextId()));
//        String appSecret = RandomUtils.randomGen(32);
//
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        details.setAppSecret(bCryptPasswordEncoder.encode(appSecret));
//        details.setResourceIds("USER-RESOURCE");
//        details.setScope("read,write");
//        details.setAuthorizedGrantTypes("authorization_code,password,refresh_token");
//        details.setRedirectUri("http://localhost:8090/index");
//        details.setAuthorities("admin");
//        details.setTrusted(0);
//        details.setCreateTime(LocalDateTime.now());
//        details.setUpdateTime(LocalDateTime.now());
//
//        oauthClientDetailsService.save(details);
//
//        log.info("保存第三方应用信息成功，appKey【{}】，appSecret【{}】，请妥善保存", details.getAppKey(), appSecret);
//    }
}