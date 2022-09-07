package team.jtq.auth.oauth_serve.service.Imp

import com.alibaba.fastjson.JSON
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import team.jtq.auth.oauth_serve.entity.OauthRole
import team.jtq.auth.oauth_serve.mapper.OauthRoleMapper
import team.jtq.auth.oauth_serve.service.OauthRoleService
import java.util.concurrent.TimeUnit
import javax.annotation.Resource

@Service
class OauthRoleServiceImp : ServiceImpl<OauthRoleMapper, OauthRole>(), OauthRoleService {

    private val REDIS_PREFIX = "ROLE"

    @Resource
    private lateinit var redisTemplate: RedisTemplate<String, Any>

    override fun confirmGeneralizableRole(roleName: String): String? {
        if (redisTemplate.hasKey(REDIS_PREFIX + roleName)) {
            val obj=  redisTemplate.opsForValue().get(REDIS_PREFIX + roleName)
            return JSON.parseObject(JSON.toJSONString(obj),String::class.java)
        } else {
            val query = KtQueryWrapper(OauthRole::class.java)
            query.eq(OauthRole::canExudes, 1)
            query.eq(OauthRole::roleName, roleName)
            val role = this.baseMapper.selectOne(query)
            if (role == null)
                return null
            else {
                redisTemplate.opsForValue().set(REDIS_PREFIX + role.roleName, role.id,3,TimeUnit.HOURS)
                return role.id
            }
        }
    }

    override fun getAllGeneralizableRole(): List<String>? {
        if(redisTemplate.hasKey("ALL$REDIS_PREFIX")) {
            val obj = redisTemplate.opsForList().range("ALL$REDIS_PREFIX", 0, -1)
            return obj!!.filterIsInstance(String::class.java).apply { if(size!=obj.size) return null}
        }else{
            return reLoadRoles(true)
        }
    }

    override fun reLoadRoles(values: Boolean):List<String>? {
        val query = KtQueryWrapper(OauthRole::class.java)
        query.eq(OauthRole::canExudes,1)
        val roles = this.baseMapper.selectList(query)
        val list = roles.map { it.roleName }
        redisTemplate.opsForList().leftPushAll("ALL$REDIS_PREFIX",list)
        return if (values)
            list
        else
            null
    }
}