package team.jtq.auth.oauth_serve.service.imp

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.util.Base64Utils
import org.springframework.util.DigestUtils
import team.jtq.auth.oauth_serve.adapter.UserDetailsAdapter
import team.jtq.auth.oauth_serve.config.OAuthServerConfig
import team.jtq.auth.oauth_serve.entity.OauthRole
import team.jtq.auth.oauth_serve.entity.OauthUser
import team.jtq.auth.oauth_serve.entity.OauthVerify
import team.jtq.auth.oauth_serve.entity.ao.RegisterEntity
import team.jtq.auth.oauth_serve.mapper.OauthUserMapper
import team.jtq.auth.oauth_serve.service.*
import team.jtq.auth.oauth_serve.tools.UnifiedCodeValidator
import java.io.Serializable
import java.time.LocalDateTime
import java.util.UUID
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors
import javax.annotation.Resource

@Service
class OauthUserDetailServiceImp : ServiceImpl<OauthUserMapper, OauthUser>(), OauthUserDetailService, Serializable {

    @Autowired
    private lateinit var userRoleService: OauthUserRoleService

    @Autowired
    private lateinit var roleService: OauthRoleService

    @Autowired
    private lateinit var emailService: EmailService

    @Autowired
    private lateinit var verifyService:OauthVerifyService

    @Autowired lateinit var userClientService: OauthUserClientService

    @Resource
    private lateinit var redisTemplate: RedisTemplate<String, Any>


    override fun loadUserByUsername(username: String): UserDetails {
        val user = this.baseMapper.getUserByAccount(username) ?: throw UsernameNotFoundException("Can Not Found User")
        return UserDetailsAdapter(user)
    }

    override fun getProtectedUserInfo(account: String): JSONObject? {
        val user = this.baseMapper.getUserByAccount(account)
        if (user != null) {
            val res = JSONObject()
            res["id"] = user.id
            res["username"] = user.userName
            res["phone"] = user.phone
            res["gender"] = user.gender
            res["status"] = user.status
            res["addition"] = user.addition
            if (!user.roleList.isEmpty()) {
                res.put("role", user.roleList.stream().map(OauthRole::roleCode).collect(Collectors.toList()))
            }
            return res
        }
        return null
    }

    override fun updateUserName(account: String, userName: String): Boolean {
        val user = this.baseMapper.getUserByAccount(account)
        if (user != null) {
            user.userName = userName
            user.updateTime = LocalDateTime.now()
            this.baseMapper.updateById(user)
            return true
        }
        return false
    }

    override fun addAccount(entity: RegisterEntity): Boolean {
        val obj = OauthUser::class.java
        val instance = obj.newInstance()
        val systemID = roleService.lodeSystemRole()
        val systemTime = LocalDateTime.now()
        val role = roleService.getAllGeneralizableRole()

        instance.account = entity.account
        instance.userName = entity.username
        instance.password = entity.password
        instance.phone = entity.phone
        instance.delFlag = 0
        instance.gender = entity.gender
        instance.status = 0
        instance.addition = entity.addition
        instance.createBy = systemID
        instance.updateBy = systemID
        instance.createTime = systemTime
        instance.updateTime = systemTime

        super<ServiceImpl>.save(instance)
        userClientService.addLinkedInUserClient(instance.id,entity.addition)
        for (i in role!!) {
            if (i.roleLevel < entity.accountLevel)
                userRoleService.registerUserRole(instance.id, i.id)
        }
        if (entity.accountLevel < OAuthServerConfig.minimumCheckLevel.toInt()) {
            val code = generateConfirmCode()
            val address =
                if (OAuthServerConfig.domainName.endsWith("/")) OAuthServerConfig.domainName else OAuthServerConfig.domainName + "/" +
                        "oauth/confirm_account/" + Base64Utils.encodeToString(instance.id.toByteArray(Charsets.UTF_8))+"/" +code
            return emailService.sendConfirmationEmail(address, entity.account, "Epiphyllum注册确认")
        } else {
            try {
                val addition = JSON.parseObject(entity.addition)
                if(addition.isEmpty())
                    return false
                if((addition["credit_code"] as String).isNotEmpty()){
                    if(UnifiedCodeValidator.validateUnifiedCreditCode(addition["credit_code"] as String)){
                        val verifyObj = OauthVerify::class.java
                        val verify = verifyObj.newInstance()
                        verify.addition = addition["credit_code"] as String
                        verify.passUser = systemID.toString()
                        verify.status = 1
                        verify.createTime = systemTime
                        verify.passTime = systemTime
                        verify.requestId = instance.id
                        verifyService.baseMapper.insert(verify)
                        return true
                    }
                    else
                    {
                        val verifyObj = OauthVerify::class.java
                        val verify = verifyObj.newInstance()
                        verify.addition = addition["other"] as String
                        verify.passUser = systemID.toString()
                        verify.status = 0
                        verify.createTime = systemTime
                        verify.passTime = systemTime
                        verify.requestId = instance.id
                        verifyService.baseMapper.insert(verify)
                        return true
                    }
                }
            } catch (e: Exception) {
                return false
            }
        }
        return false
    }



    override fun verifyConfirmationCode(code: String, id: String): Boolean {
        val key = DigestUtils.md5DigestAsHex(code.toByteArray(Charsets.UTF_8))
        if(redisTemplate.hasKey("CONFIRMCODE:$key")){
            redisTemplate.delete("CONFIRMCODE:$key")
            val uid = Base64Utils.decodeFromString(id)
            val query = KtUpdateWrapper(OauthUser::class.java)
            query.eq(OauthUser::id,uid).set(OauthUser::status,1).set(OauthUser::updateTime,LocalDateTime.now())
            val row = this.baseMapper.update(null,query)
            return row!=0

        }
        else
            return false
    }

    private fun generateConfirmCode(): String {
        val confirmUUID = UUID.randomUUID().toString().replace("-", "")
        val UUIDKey = DigestUtils.md5DigestAsHex(confirmUUID.toByteArray(Charsets.UTF_8))
        redisTemplate.opsForValue()
            .set("CONFIRMCODE:$UUIDKey", confirmUUID, OAuthServerConfig.maxConfirmCodeLive.toLong(), TimeUnit.MINUTES)
        return confirmUUID
    }


}