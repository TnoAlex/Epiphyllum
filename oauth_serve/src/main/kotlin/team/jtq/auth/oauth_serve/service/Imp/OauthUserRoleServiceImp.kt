package team.jtq.auth.oauth_serve.service.Imp

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import team.jtq.auth.oauth_serve.entity.OauthUserRole
import team.jtq.auth.oauth_serve.mapper.OauthUserRoleMapper

@Service
class OauthUserRoleServiceImp:ServiceImpl<OauthUserRoleMapper,OauthUserRole>() {
}