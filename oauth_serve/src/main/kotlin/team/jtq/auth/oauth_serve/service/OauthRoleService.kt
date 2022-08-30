package team.jtq.auth.oauth_serve.service

import com.baomidou.mybatisplus.extension.service.IService
import team.jtq.auth.oauth_serve.entity.OauthRole

interface OauthRoleService:IService<OauthRole> {
    fun insterBean(bean:OauthRole)
}