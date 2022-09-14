package team.jtq.epi_serve.service.imp

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import team.jtq.epi_serve.config.AppResourceConfig
import team.jtq.epi_serve.entity.ResultStatusCode
import team.jtq.epi_serve.service.TokenService
import team.jtq.epi_serve.tools.Result

@Service
class TokenServiceImp:TokenService {

    @Autowired
    private lateinit var restTemplate:RestTemplate

    override fun getUserParameter(token: String): Result {
        if(token.isEmpty())
            return Result.error(ResultStatusCode.TOKEN_MISS)
        val responseBody: ResponseEntity<String>
        val reqUrl = AppResourceConfig.oauthService+ AppResourceConfig.checkTokenUri+"token="+token
        try{
            responseBody  = restTemplate.postForEntity(reqUrl,null,String::class.java)
        }catch (e:Exception)
        {
            return Result.error(ResultStatusCode.AUTHENTICATION_CONNECT_ERR)
        }
        if(responseBody.body == null )
            return Result.error(ResultStatusCode.INVALID_TOKEN)
        if(responseBody.body!!.contains("error"))
        {
            return Result.error(ResultStatusCode.INVALID_TOKEN)
        }
        if(responseBody.body!!.contains("client_id")){
            val json = JSON.parseObject(responseBody.body)
            val res = JSONObject()
            res["user_account"] = json["user_account"]
            res["user_name"] = json["user_name"]
            res["user_gender"] = json["user_gender"]
            res["user_phone"] = json["user_phone"]
            res["user_status"] = json["user_status"]
            return Result.ok(res)
        }
        return Result.error(ResultStatusCode.UNKONW_ERROR)
    }

}