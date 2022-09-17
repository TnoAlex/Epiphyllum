package team.jtq.auth.oauth_serve.service.imp

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import lombok.extern.slf4j.Slf4j
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.provider.ClientDetails
import org.springframework.stereotype.Service
import org.springframework.util.Base64Utils
import team.jtq.auth.oauth_serve.adapter.ClientDetailsAdapter
import team.jtq.auth.oauth_serve.entity.ao.OauthAppRegister
import team.jtq.auth.oauth_serve.entity.OauthClientDetails
import team.jtq.auth.oauth_serve.mapper.OauthClientDetailsMapper
import team.jtq.auth.oauth_serve.service.OauthClientDetailsService
import javax.annotation.Resource
import java.util.concurrent.TimeUnit
import team.jtq.auth.oauth_serve.tools.Result
import java.time.LocalDateTime
import java.util.*

@Slf4j
@Service("oauthClientDetailsService")
class OauthClientDetailsServiceImp : ServiceImpl<OauthClientDetailsMapper, OauthClientDetails>(),
    OauthClientDetailsService{

    @Resource
    private lateinit var redisTemplate: RedisTemplate<String, Any>


    private val prefix = "ClientDetails:"

    override fun loadClientByClientId(clientId: String): ClientDetails? {
        val obj = redisTemplate.opsForValue().get(prefix + clientId)
        var oauthClientDetils = JSON.parseObject(JSON.toJSONString(obj), OauthClientDetails::class.java)
        if (oauthClientDetils == null) {
            val query = KtQueryWrapper(OauthClientDetails::class.java)
//            val query = QueryWrapper<OauthClientDetails>()
            query.eq(OauthClientDetails::appKey, clientId)
            oauthClientDetils = this.baseMapper.selectOne(query)

            if (oauthClientDetils == null)
                return null
            redisTemplate.opsForValue().set(prefix + clientId, oauthClientDetils, 1, TimeUnit.HOURS)
        }
        return ClientDetailsAdapter(oauthClientDetils)
    }

    override fun registApp(entity: OauthAppRegister): Result {
        val obj = OauthClientDetails::class.java
        val client = obj.newInstance()
        val appid = UUID.randomUUID().toString().replace("-", "")
        val secret = UUID.randomUUID().toString().replace("-","")
        val appSecret = BCryptPasswordEncoder().encode(secret)
        val systemTime = LocalDateTime.now()
        client.appKey = appid
        client.appSecret = appSecret
        client.createTime = systemTime
        client.updateTime = systemTime
        client.refreshTokenValidity = entity.refreshTokenValidity
        client.accessTokenValidity = entity.accessTokenValidity
        client.appName = entity.appName
        client.archived = 0
        client.trusted = entity.trusted
        client.createBy = entity.opId
        client.scope = entity.scope
        client.updateBy = entity.opId
        client.authorities = entity.authorities
        client.redirectUri = entity.redirectUri
        client.resourceIds ="USER-RESOURCE"
        client.authorizedGrantTypes = entity.authorizedGrantTypes
        client.autoapprove = "true"
        super<ServiceImpl>.save(client)
        val json = JSONObject()
        json["appid"] = client.appKey
        json["app secret"] = Base64Utils.encodeToString(appSecret.toByteArray(Charsets.UTF_8))
        return Result.ok(json)
    }
}