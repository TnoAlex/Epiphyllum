package team.jtq.auth.oauth_serve.service.Imp

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.oauth2.provider.ClientDetails
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import team.jtq.auth.oauth_serve.adapter.ClientDetailsAdapter
import team.jtq.auth.oauth_serve.entity.OauthClientDetails
import team.jtq.auth.oauth_serve.mapper.OauthClientDetailsMapper
import team.jtq.auth.oauth_serve.service.OauthClientDetailsService
import javax.annotation.Resource
import java.util.concurrent.TimeUnit

@Slf4j
@Service("oauthClientDetailsService")
class OauthClientDetailsServiceImp :ServiceImpl<OauthClientDetailsMapper, OauthClientDetails>(),OauthClientDetailsService,ClientDetailsService{
    @Resource
    private lateinit var redisTemplate: RedisTemplate<String, OauthClientDetails>
    private val prefix = "ClientDetails:"

    override fun loadClientByClientId(clientId: String): ClientDetails? {
       var oauthClientDetils = redisTemplate.opsForValue().get(prefix+clientId)
        if(oauthClientDetils == null){
            val query  = LambdaQueryWrapper<OauthClientDetails>()
            query.eq(OauthClientDetails::appKey,clientId)
            oauthClientDetils = super<ServiceImpl>.getOne(query)
            if(oauthClientDetils == null)
                return null
            redisTemplate.opsForValue().set(prefix+clientId, oauthClientDetils,1,TimeUnit.HOURS)
        }
        return ClientDetailsAdapter(oauthClientDetils)
    }
}