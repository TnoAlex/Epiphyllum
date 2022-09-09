package team.jtq.auth.oauth_serve.service.imp

import com.alibaba.fastjson.JSONObject
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils
import team.jtq.auth.oauth_serve.adapter.UserDetailsAdapter
import team.jtq.auth.oauth_serve.config.AppResourceConfig
import team.jtq.auth.oauth_serve.entity.OauthRole
import team.jtq.auth.oauth_serve.entity.OauthUser
import team.jtq.auth.oauth_serve.entity.RegisterEntity
import team.jtq.auth.oauth_serve.mapper.OauthUserMapper
import team.jtq.auth.oauth_serve.service.EmailService
import team.jtq.auth.oauth_serve.service.OauthRoleService
import team.jtq.auth.oauth_serve.service.OauthUserDetailService
import team.jtq.auth.oauth_serve.service.OauthUserRoleService
import java.io.Serializable
import java.time.LocalDateTime
import java.util.UUID
import java.util.stream.Collectors
import javax.annotation.Resource

@Service
class OauthUserDetailServiceImp : ServiceImpl<OauthUserMapper, OauthUser>(), OauthUserDetailService, UserDetailsService, Serializable {

    @Resource
    private lateinit var mapper: OauthUserMapper

    @Autowired
    private lateinit var userRoleService: OauthUserRoleService

    @Autowired
    private lateinit var roleService: OauthRoleService

    @Resource
    private lateinit var redisTemplate: RedisTemplate<String,Any>

    @Autowired
    private lateinit var emailService: EmailService


    override fun loadUserByUsername(username: String): UserDetails {
        val user = mapper.getUserByAccount(username) ?: throw UsernameNotFoundException("Can Not Found User")
        return UserDetailsAdapter(user)
    }

    override fun getProtectedUserInfo(account: String): JSONObject? {
        val user = mapper.getUserByAccount(account)
        if (user != null) {
            val res = JSONObject()
            res["id"] = user.id
            res["userName"] = user.userName
            res["phone"] = user.phone
            res["gender"] = user.gender
            res["addition"] = user.addition
            res["sattus"] = user.status
            if(!user.roleList.isEmpty()){
                res.put("role",user.roleList.stream().map(OauthRole::roleCode).collect(Collectors.toList()))
            }
            return res
        }
        return null
    }

    override fun updateUserName(account:String, userName:String): Boolean {
        val user = mapper.getUserByAccount(account)
        if(user!=null){
            user.userName=userName
            user.updateTime = LocalDateTime.now()
            mapper.updateById(user)
            return true
        }
        return false
    }

    override fun addAccount(entity: RegisterEntity): Boolean {
        val obj = OauthUser::class.java
        val instance = obj.newInstance()
        val systemID  = roleService.lodeSystemRole()
        val servreTime = LocalDateTime.now()
        val role = roleService.getAllGeneralizableRole()

        instance.account = entity.account
        instance.userName =entity.username
        instance.password = entity.password
        instance.addition = entity.addition
        instance.phone = entity.phone
        instance.delFlag = 0
        instance.gender = entity.gender
        instance.status = 0
        instance.createBy = systemID
        instance.updateBy = systemID
        instance.createTime = servreTime
        instance.updateTime = servreTime

        if(entity.accountLevel < AppResourceConfig.minimumCheckLevel.toInt()){
            super<ServiceImpl>.save(instance)
            for(i in role!!){
                if(i.roleLevel<entity.accountLevel)
                    userRoleService.registerUserRole(instance.id,i.id)
            }
            val code = generateConfirmCode()
            val address = if(AppResourceConfig.domainName.endsWith("/")) AppResourceConfig.domainName else AppResourceConfig.domainName+"/" + "oauth/confirm_account/"+code
            return emailService.sendConfirmationEmail(address,entity.account,"Epiphyllum注册确认")
        }
        TODO("Not yet implemented")
    }

    override fun addCertificationRequiredAccount(entity: OauthUser): Boolean {
        TODO("Not yet implemented")
    }

    override fun generateConfirmCode(): String {
        val confirmUUID = UUID.randomUUID().toString().replace("-","")
        val UUIDKey = DigestUtils.md5DigestAsHex(confirmUUID.toByteArray(Charsets.UTF_8))
        redisTemplate.opsForValue().set("CONFIRMCODE:$UUIDKey",confirmUUID)
        return confirmUUID
    }

}