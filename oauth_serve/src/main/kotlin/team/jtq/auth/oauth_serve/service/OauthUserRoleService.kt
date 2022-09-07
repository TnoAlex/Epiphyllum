package team.jtq.auth.oauth_serve.service

import com.baomidou.mybatisplus.extension.service.IService
import team.jtq.auth.oauth_serve.entity.OauthUserRole

interface OauthUserRoleService:IService<OauthUserRole> {
    fun registerUserRole(userId:String,roleID:String):Boolean
}