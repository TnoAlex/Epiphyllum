package team.jtq.auth.oauth_serve.service.Imp

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import team.jtq.auth.oauth_serve.entity.OauthRole
import team.jtq.auth.oauth_serve.mapper.OauthRoleMapper
import team.jtq.auth.oauth_serve.service.OauthRoleService

@Service
class OauthRoleServiceImp :ServiceImpl<OauthRoleMapper, OauthRole>(),OauthRoleService{
    override fun insterBean(bean: OauthRole) {
        this.baseMapper.insert(bean)
    }
}