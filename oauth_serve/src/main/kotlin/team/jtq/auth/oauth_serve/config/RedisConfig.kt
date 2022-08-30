package team.jtq.auth.oauth_serve.config

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore
import team.jtq.auth.oauth_serve.tools.serializer.FastJsonRedisTokenStoreSerializationStrategy
import javax.annotation.Resource


@Configuration
class RedisConfig {

    @Resource
    private lateinit var lettuceConnectionFactory: LettuceConnectionFactory
    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        // 设置序列化
        val jackson2JsonRedisSerializer = Jackson2JsonRedisSerializer(Any::class.java)
        val om = ObjectMapper()
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
        jackson2JsonRedisSerializer.setObjectMapper(om)
        // 配置redisTemplate
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.setConnectionFactory(lettuceConnectionFactory)
        val stringSerializer: RedisSerializer<*> = StringRedisSerializer()
        redisTemplate.keySerializer = stringSerializer // key序列化
        redisTemplate.valueSerializer = jackson2JsonRedisSerializer // value序列化
        redisTemplate.hashKeySerializer = stringSerializer // Hash key序列化
        redisTemplate.hashValueSerializer = jackson2JsonRedisSerializer // Hash value序列化
        redisTemplate.afterPropertiesSet()
        return redisTemplate
    }

    @Bean
    fun tokenStore(connectionFactory: RedisConnectionFactory): RedisTokenStore {
        val redisTokenStore = RedisTokenStore(connectionFactory)
        redisTokenStore.setPrefix("TOKEN:")
        redisTokenStore.setSerializationStrategy(FastJsonRedisTokenStoreSerializationStrategy())
        return redisTokenStore
    }

}