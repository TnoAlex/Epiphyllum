package team.jtq.auth.oauth_serve.service

import com.alibaba.fastjson.JSONObject
import com.baomidou.mybatisplus.extension.service.IService
import org.springframework.security.core.userdetails.UserDetailsService
import team.jtq.auth.oauth_serve.entity.OauthUser
import team.jtq.auth.oauth_serve.entity.RegisterEntity

interface OauthUserDetailService:IService<OauthUser>,UserDetailsService{
    fun getProtectedUserInfo(account: String): JSONObject?
    fun updateUserName(account:String,userName:String): Boolean
    fun addAccount(entity: RegisterEntity):Boolean
    fun verifyConfirmationCode(code: String, id: String): Boolean
}