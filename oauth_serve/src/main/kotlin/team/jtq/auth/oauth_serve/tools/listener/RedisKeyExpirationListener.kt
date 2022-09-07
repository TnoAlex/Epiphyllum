package team.jtq.auth.oauth_serve.tools.listener

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.stereotype.Component
import team.jtq.auth.oauth_serve.service.OauthRoleService

@Component
class RedisKeyExpirationListener(listenerContainer: RedisMessageListenerContainer) :
    KeyExpirationEventMessageListener(listenerContainer) {

    @Autowired
    private lateinit var roleService: OauthRoleService

    private val logger = KotlinLogging.logger {}
    override fun onMessage(message: Message, pattern: ByteArray?) {
        if(message.toString().contains("ROLE_LIST")){
            roleService.reLoadRoles(false)
        }
    }
}