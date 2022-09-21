package team.jtq.epi_serve.config

import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import javax.annotation.Resource


class RedisKeyExpiredListener(listenerContainer: RedisMessageListenerContainer) :
    KeyExpirationEventMessageListener(listenerContainer) {

    override fun onMessage(message: Message,pattern: ByteArray?) {
        val expiredKey = message.toString()
        if(expiredKey.startsWith("POST")){
            AppResourceConfig.forceRefresh("POST",expiredKey)
        }
    }

}