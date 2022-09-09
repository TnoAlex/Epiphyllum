package team.jtq.auth.oauth_serve.service.imp

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import team.jtq.auth.oauth_serve.entity.OauthUserRole
import team.jtq.auth.oauth_serve.mapper.OauthUserRoleMapper
import team.jtq.auth.oauth_serve.service.OauthUserRoleService

@Service
class OauthUserRoleServiceImp : ServiceImpl<OauthUserRoleMapper, OauthUserRole>(), OauthUserRoleService {
    override fun registerUserRole(userId: String, roleID: String): Boolean {
        try {
            val obj = OauthUserRole::class.java
            val objectInstance = obj.newInstance()
            objectInstance .userId = userId
            objectInstance .roleId = roleID
            this.baseMapper.insert(objectInstance)
        }catch (e:Exception){
            return false
        }
        return true
    }
}