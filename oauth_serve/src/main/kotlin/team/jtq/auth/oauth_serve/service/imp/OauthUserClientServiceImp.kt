package team.jtq.auth.oauth_serve.service.imp

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import team.jtq.auth.oauth_serve.entity.OauthUserClient
import team.jtq.auth.oauth_serve.mapper.OauthUserClientMapper
import team.jtq.auth.oauth_serve.service.OauthUserClientService

@Service
class OauthUserClientServiceImp:ServiceImpl<OauthUserClientMapper,OauthUserClient>(), OauthUserClientService {

    override fun addLinkedInUserClient(uid: String, cid: String) {
        val obj = OauthUserClient::class.java
        val instant = obj.newInstance()
        instant.clientId = cid;
        instant.uid = uid;
        super<ServiceImpl>.save(instant)
    }

    override fun getClientbyUser(uid: String): String {
        val query = KtQueryWrapper(OauthUserClient::class.java)
        query.eq(OauthUserClient::uid,uid)
        val obj = this.baseMapper.selectOne(query)
        return obj?.clientId ?: ""
    }

}