package team.jtq.auth.oauth_serve.service.Imp

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.core.toolkit.support.SFunction
import com.baomidou.mybatisplus.core.toolkit.support.SerializedLambda
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.extern.slf4j.Slf4j
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.oauth2.provider.ClientDetails
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.stereotype.Service
import team.jtq.auth.oauth_serve.adapter.ClientDetailsAdapter
import team.jtq.auth.oauth_serve.entity.OauthClientDetails
import team.jtq.auth.oauth_serve.mapper.OauthClientDetailsMapper
import team.jtq.auth.oauth_serve.service.OauthClientDetailsService
import javax.annotation.Resource
import java.util.concurrent.TimeUnit

@Slf4j
@Service("oauthClientDetailsService")
class OauthClientDetailsServiceImp : ServiceImpl<OauthClientDetailsMapper, OauthClientDetails>(),
    OauthClientDetailsService, ClientDetailsService {
    @Resource
    private lateinit var redisTemplate: RedisTemplate<String, Any>
    private val prefix = "ClientDetails:"

    override fun loadClientByClientId(clientId: String): ClientDetails? {
        val obj = redisTemplate.opsForValue().get(prefix + clientId)
        var oauthClientDetils = JSON.parseObject(JSON.toJSONString(obj), OauthClientDetails::class.java)
        if (oauthClientDetils == null) {
            val query = KtQueryWrapper(OauthClientDetails::class.java)
//            val query = QueryWrapper<OauthClientDetails>()
            query.eq(OauthClientDetails::appKey,clientId)
           oauthClientDetils = this.baseMapper.selectOne(query)

            if (oauthClientDetils == null)
                return null
            redisTemplate.opsForValue().set(prefix + clientId, oauthClientDetils, 1, TimeUnit.HOURS)
        }
        return ClientDetailsAdapter(oauthClientDetils)
    }
}