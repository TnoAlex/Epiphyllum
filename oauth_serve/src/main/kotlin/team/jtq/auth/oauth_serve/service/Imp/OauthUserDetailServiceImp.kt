package team.jtq.auth.oauth_serve.service.Imp

import com.alibaba.fastjson.JSONObject
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import team.jtq.auth.oauth_serve.adapter.UserDetailsAdapter
import team.jtq.auth.oauth_serve.entity.OauthRole
import team.jtq.auth.oauth_serve.entity.OauthUser
import team.jtq.auth.oauth_serve.entity.RegisterEntity
import team.jtq.auth.oauth_serve.mapper.OauthUserMapper
import team.jtq.auth.oauth_serve.service.OauthUserDetailService
import team.jtq.auth.oauth_serve.service.OauthUserRoleService
import java.io.Serializable
import java.time.LocalDateTime
import java.util.stream.Collectors
import javax.annotation.Resource

@Service
class OauthUserDetailServiceImp : ServiceImpl<OauthUserMapper, OauthUser>(), OauthUserDetailService, UserDetailsService, Serializable {
    private val serialVersionUID = 1170885289644276974L

    @Resource
    private lateinit var mapper: OauthUserMapper

    @Autowired
    private lateinit var userRoleService: OauthUserRoleService

    @Autowired
    private lateinit var roleService: OauthUserRoleService

    override fun loadUserByUsername(username: String): UserDetails {
        val user = mapper.getUserByAccount(username) ?: throw UsernameNotFoundException("Can Not Found User")
        return UserDetailsAdapter(user)
    }

    override fun getProtectedUserInfo(account: String): JSONObject? {
        val user = mapper.getUserByAccount(account)
        if (user != null) {
            val res = JSONObject()
            res["id"] = user.id
            res["userName"] = user.userName
            res["phone"] = user.phone
            res["gender"] = user.gender
            res["addition"] = user.addition
            if(!user.roleList.isEmpty()){
                res.put("role",user.roleList.stream().map(OauthRole::roleCode).collect(Collectors.toList()))
            }
            return res
        }
        return null
    }

    override fun updateUserName(account:String, userName:String): Boolean {
        val user = mapper.getUserByAccount(account)
        if(user!=null){
            user.userName=userName
            user.updateTime = LocalDateTime.now()
            mapper.updateById(user)
            return true
        }
        return false
    }

    override fun addAccount(entity: RegisterEntity): Boolean {

        TODO("Not yet implemented")
    }

}