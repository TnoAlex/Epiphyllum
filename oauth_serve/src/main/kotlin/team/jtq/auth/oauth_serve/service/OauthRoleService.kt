package team.jtq.auth.oauth_serve.service

import com.baomidou.mybatisplus.extension.service.IService
import team.jtq.auth.oauth_serve.entity.OauthRole

interface OauthRoleService:IService<OauthRole> {
    fun confirmGeneralizableRole(roleName: String): String?
    fun getAllGeneralizableRole():List<String>?
    fun reLoadRoles(values: Boolean):List<String>?
}