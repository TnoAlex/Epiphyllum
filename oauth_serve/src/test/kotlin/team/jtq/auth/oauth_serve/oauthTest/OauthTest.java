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
import team.jtq.auth.oauth_serve.service.imp.OauthClientDetailsServiceImp;
import team.jtq.auth.oauth_serve.service.imp.OauthRoleServiceImp;
import team.jtq.auth.oauth_serve.service.imp.OauthUserDetailServiceImp;
import team.jtq.auth.oauth_serve.service.imp.OauthUserRoleServiceImp;

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
   public void password(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92"));
        System.out.println("1231");
    }
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
    public void  testSql(){
        userRoleService.registerUserRole("123","321");
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