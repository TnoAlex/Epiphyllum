package team.jtq.auth.oauth_serve.service

import com.baomidou.mybatisplus.extension.service.IService
import team.jtq.auth.oauth_serve.entity.OauthUserClient

interface OauthUserClientService:IService<OauthUserClient> {
    fun addLinkedInUserClient(uid:String, cid:String)
    fun getClientbyUser(uid:String):String
}