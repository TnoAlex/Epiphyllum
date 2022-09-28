package team.jtq.epi_serve.config

import com.alibaba.fastjson.JSON
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import team.jtq.epi_serve.entity.UsdPost
import team.jtq.epi_serve.mapper.UsdPostMapper
import javax.annotation.Resource


@Component
@ConfigurationProperties(prefix = "authorization-server")
object AppResourceConfig {
    lateinit var appResourcesId: String
    lateinit var appQuerySecret: String
    lateinit var oauthService: String
    lateinit var queryTokenUri: String
    lateinit var checkTokenUri: String
    lateinit var remoteSingup: String
    lateinit var UTCTimeZone: String
    lateinit var RSAPath:String
    lateinit var appCachePath:String
    lateinit var defaultUserIco:String

    @Autowired
    lateinit var redisTemplate:RedisTemplate<String,Any>
    fun forceRefresh(series_prefix:String,key:String){
        when(series_prefix){
            "POST"->{
                val mapper = BeanContext.getBeanbyClazz(UsdPostMapper::class.java)
                val obj = redisTemplate.opsForValue().get(key)
                val post = JSON.parseObject(JSON.toJSONString(obj), UsdPost::class.java)
                mapper.updateById(post)
            }
        }
    }

    fun forceRefreshFolder(series_prefix:String){
        when(series_prefix){
            "POST_CACHE"->{
                val mapper = BeanContext.getBeanbyClazz(UsdPostMapper::class.java)
                val keys = redisTemplate.keys(series_prefix)
                for(k in keys){
                    val obj = redisTemplate.opsForValue().get(k)
                    val post = JSON.parseObject(JSON.toJSONString(obj), UsdPost::class.java)
                    mapper.updateById(post)
                }
            }
        }
    }
}
