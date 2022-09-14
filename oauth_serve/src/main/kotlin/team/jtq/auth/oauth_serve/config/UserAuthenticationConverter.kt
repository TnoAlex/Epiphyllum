package team.jtq.auth.oauth_serve.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter
import org.springframework.stereotype.Component
import org.springframework.util.Base64Utils
import team.jtq.auth.oauth_serve.adapter.ClientDetailsAdapter
import team.jtq.auth.oauth_serve.adapter.UserDetailsAdapter
import team.jtq.auth.oauth_serve.service.OauthClientDetailsService
import team.jtq.auth.oauth_serve.service.OauthUserClientService
import java.security.KeyFactory
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

class UserAuthenticationConverter: DefaultUserAuthenticationConverter() {

    var userClientService:OauthUserClientService = BeanContext.getBean(OauthUserClientService::class.java)
    var clientDetailsService:OauthClientDetailsService =BeanContext.getBean(OauthClientDetailsService::class.java)

    override fun convertUserAuthentication(authentication: Authentication): MutableMap<String, *> {
        var response = LinkedHashMap<String,Any>()
        val principal = authentication.principal as UserDetailsAdapter
        response["user_name"] = principal.nickname
        response["user_account"] = principal.username
        response["user_gender"] = principal.gender
        response["user_phone"] = principal.phone
        response["user_status"] = principal.status.toString()
        response["user_id"] = principal.id
        val client = userClientService.getClientbyUser(principal.id)
//        response = rSAEncoder(response,client)
        if (authentication.authorities != null && !authentication.authorities.isEmpty()) {
            response[AUTHORITIES] = AuthorityUtils.authorityListToSet(authentication.authorities)
        }
        return response
        TODO("开发过程中暂时禁用加密")
    }

    private fun rSAEncoder(value:LinkedHashMap<String,Any>, id:String): LinkedHashMap<String, Any> {
        val client = clientDetailsService.loadClientByClientId(id) as ClientDetailsAdapter
        val key = client.getClientDetails().appPublicKey.replace("\r\n","")
        val decodeKey = Base64Utils.decodeFromString(key)
        val x509KeySpec = X509EncodedKeySpec(decodeKey)
        val keyFactory = KeyFactory.getInstance("RSA")
        val publicKey = keyFactory.generatePublic(x509KeySpec)
        val cipher = Cipher.getInstance(keyFactory.algorithm)
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        val res = LinkedHashMap<String,Any>()
        for((k,v)in value){
            val sv = cipher.doFinal((v as String).toByteArray(Charsets.UTF_8))
            res[k] = String(sv)
        }
        return res
    }
}