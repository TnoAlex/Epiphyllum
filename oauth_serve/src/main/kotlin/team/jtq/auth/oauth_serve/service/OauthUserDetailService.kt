package team.jtq.auth.oauth_serve.service

import com.alibaba.fastjson.JSONObject
import com.baomidou.mybatisplus.extension.service.IService
import team.jtq.auth.oauth_serve.entity.OauthUser

interface OauthUserDetailService:IService<OauthUser> {
    fun getProtectedUserInfo(account: String): JSONObject?
    fun updateUserName(account:String,userName:String): Boolean
}