package team.jtq.auth.oauth_serve.tools.serializer

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.parser.ParserConfig
import com.alibaba.fastjson.serializer.SerializerFeature
import com.alibaba.fastjson.util.IOUtils
import com.alibaba.fastjson.util.TypeUtils
import com.baomidou.mybatisplus.core.toolkit.Assert
import org.springframework.data.redis.serializer.SerializationException
import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.client.BaseClientDetails
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStoreSerializationStrategy
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import team.jtq.auth.oauth_serve.adapter.UserDetailsAdapter

class FastJsonRedisTokenStoreSerializationStrategy: RedisTokenStoreSerializationStrategy {
    private lateinit var defaultRedisConfig:ParserConfig

    init {
        defaultRedisConfig = ParserConfig()
        defaultRedisConfig.isAutoTypeSupport = true
        defaultRedisConfig.putDeserializer(DefaultOAuth2RefreshToken::class.java, DefaultOauth2RefreshTokenSerializer())
        defaultRedisConfig.putDeserializer(OAuth2Authentication::class.java, OAuth2AuthenticationSerializer())
        defaultRedisConfig.addAccept("org.springframework.security.oauth2.provider.")
        defaultRedisConfig.addAccept("org.springframework.security.oauth2.provider.client")
        TypeUtils.addMapping("org.springframework.security.oauth2.provider.OAuth2Authentication", OAuth2Authentication::class.java)
        TypeUtils.addMapping("org.springframework.security.oauth2.provider.client.BaseClientDetails", BaseClientDetails::class.java)
        defaultRedisConfig.addAccept("org.springframework.security.oauth2.common.")
        TypeUtils.addMapping("org.springframework.security.oauth2.common.DefaultOAuth2AccessToken", DefaultOAuth2AccessToken::class.java)
        TypeUtils.addMapping("org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken", DefaultExpiringOAuth2RefreshToken::class.java)
        defaultRedisConfig.addAccept("team.jtq.auth.oauth_serve.adapter")
        TypeUtils.addMapping("team.jtq.auth.oauth_serve.adapter", UserDetailsAdapter::class.java)
        defaultRedisConfig.addAccept("org.springframework.security.web.authentication.preauth")
        TypeUtils.addMapping("org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken", PreAuthenticatedAuthenticationToken::class.java)
    }

    override fun <T> deserialize(bytes: ByteArray?, clazz: Class<T>?): T? {
        Assert.notNull(clazz, "clazz can't be null")
        if (bytes == null || bytes.size == 0)
            return null
        else {
            return JSON.parseObject<T>(String(bytes, IOUtils.UTF8), clazz, defaultRedisConfig)
        }
    }

    override fun deserializeString(bytes: ByteArray?): String? {
        return if (bytes == null || bytes.size == 0) {
            null
        } else String(bytes, IOUtils.UTF8)
    }

    override fun serialize(obj: Any?): ByteArray? {
        return if (obj == null) {
            ByteArray(0)
        } else try {
            JSON.toJSONBytes(
                obj, SerializerFeature.WriteClassName,
                SerializerFeature.DisableCircularReferenceDetect
            )
        } catch (e: Exception) {
            throw SerializationException("Could not serialize: " + e.message, e)
        }
    }

    override fun serialize(data: String): ByteArray? {
        return if (data.isEmpty()) {
            ByteArray(0)
        } else data.toByteArray(IOUtils.UTF8)
    }

}