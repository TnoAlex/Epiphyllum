package team.jtq.auth.oauth_serve.service.imp

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import team.jtq.auth.oauth_serve.entity.OauthVerify
import team.jtq.auth.oauth_serve.mapper.OauthVerifyMapper
import team.jtq.auth.oauth_serve.service.OauthVerifyService


@Service
class OauthVerifyServiceImp: ServiceImpl<OauthVerifyMapper, OauthVerify>(),OauthVerifyService {

}