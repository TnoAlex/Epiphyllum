package team.jtq.auth.oauth_serve.config

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jdk8.Jdk8Serializers
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer
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
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
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
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
        om.registerModule(JodaModule())
        om.registerModule(Jdk8Module())
        val javaTimeModule = JavaTimeModule()
        //格式化时间格式
        javaTimeModule.addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
        javaTimeModule.addSerializer(LocalDate::class.java,  LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
        javaTimeModule.addSerializer(LocalTime::class.java, LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")))
        om.registerModule(javaTimeModule)
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