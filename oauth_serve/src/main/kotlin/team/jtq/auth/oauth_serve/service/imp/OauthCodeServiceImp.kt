package team.jtq.auth.oauth_serve.service.imp

import com.alibaba.fastjson.JSON
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator
import org.springframework.security.oauth2.common.util.SerializationUtils
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices
import org.springframework.stereotype.Service
import team.jtq.auth.oauth_serve.entity.OauthCode
import team.jtq.auth.oauth_serve.mapper.OauthCodeMapper
import team.jtq.auth.oauth_serve.service.OauthCodeService
import java.util.concurrent.TimeUnit
import javax.annotation.Resource


@Service
class OauthCodeServiceImp :ServiceImpl<OauthCodeMapper, OauthCode>(),AuthorizationCodeServices,OauthCodeService {

    @Resource
    private lateinit var redisTemplate:RedisTemplate<String,Any>

    private val generator = RandomValueStringGenerator(10)

    private val prefix = "AuthorizationCode:"

    override fun createAuthorizationCode(authentication: OAuth2Authentication): String {
        val code = generator.generate()
        store(code,authentication)
        return code
    }

    override fun consumeAuthorizationCode(code: String): OAuth2Authentication {
       return remove(code) ?: throw InvalidGrantException("Invalid authorization code: " + code)
    }

    override fun store(code: String, authentication: OAuth2Authentication) {
        val oauthCode = OauthCode(code,SerializationUtils.serialize(authentication))
        redisTemplate.opsForValue()[prefix + code, oauthCode, 5] = TimeUnit.MINUTES
    }

    override fun remove(code: String): OAuth2Authentication? {
        val obj = redisTemplate.opsForValue().get(prefix+code)
        val oauthCode = JSON.parseObject(JSON.toJSONString(obj), OauthCode::class.java)
        if (oauthCode != null) {
            redisTemplate.delete(prefix + code)
            return SerializationUtils.deserialize(oauthCode.authentication)
        }
        return null
    }


}