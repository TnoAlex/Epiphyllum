package team.jtq.auth.oauth_serve.service.imp

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

    private val REDIS_PREFIX = "ALLROLE"

    @Resource
    private lateinit var redisTemplate: RedisTemplate<String, Any>

    override fun confirmGeneralizableRole(roleName: String): Boolean {
        val list = getAllGeneralizableRole() ?: return false
        for(i in list){
            if(roleName != i.roleName)
                return false
        }
        return false
    }

    override fun getAllGeneralizableRole(): Collection<OauthRole>? {
        if(redisTemplate.hasKey(REDIS_PREFIX)) {
            val obj = redisTemplate.opsForList().range(REDIS_PREFIX, 0, -1)
            val res = ArrayList<OauthRole>()
            for(i in obj!!){
                val r = JSON.parseObject(JSON.toJSONString(i),OauthRole::class.java)
                res.add(r)
            }
            return res
        }else{
            return reLoadRoles()
        }
    }

    override fun reLoadRoles(): Collection<OauthRole> {
        val query = KtQueryWrapper(OauthRole::class.java)
        query.eq(OauthRole::canExudes,1)
        val roles = this.baseMapper.selectList(query)
        redisTemplate.opsForList().leftPushAll(REDIS_PREFIX, roles as Collection<Any>)
        return roles
    }

    override fun lodeSystemRole(): Long {
        if(redisTemplate.hasKey("SYSTEM-ROLE")){
            val obj = redisTemplate.opsForValue().get("SYSTEM-ROLE")
            return JSON.parseObject(JSON.toJSONString(obj),String::class.java).toLong()
        }
        else{
            val query = KtQueryWrapper(OauthRole::class.java)
            query.eq(OauthRole::roleCode,"System")
            val role = this.baseMapper.selectOne(query)
            redisTemplate.opsForValue().set("SYSTEM-ROLE",role.id,1,TimeUnit.DAYS)
            return role.id.toLong()
        }
    }
}