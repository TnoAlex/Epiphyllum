package team.jtq.epi_serve.service.Imp

import com.alibaba.fastjson.JSON
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils
import org.springframework.web.client.RestTemplate
import team.jtq.epi_serve.config.AppResourceConfig
import team.jtq.epi_serve.entity.LoginEntity
import team.jtq.epi_serve.entity.ResultStatusCode
import team.jtq.epi_serve.entity.Token
import team.jtq.epi_serve.service.LoginService
import team.jtq.epi_serve.tools.Result
import team.jtq.epi_serve.tools.VerificationCodeGenerater
import java.util.*
import java.util.concurrent.TimeUnit
import javax.annotation.Resource


@Service
class LoginServiceImp:LoginService {

    @Autowired
    private lateinit var restTemplate: RestTemplate
    @Resource
    private lateinit var redisTemplate: RedisTemplate<String, String>
    @Autowired
    private lateinit var codeGenerater: VerificationCodeGenerater

    override fun verificationCodeGeneration(timestamp: String): String {
        val codePair = codeGenerater.generate()
        val realKey = DigestUtils.md5DigestAsHex((codePair[0]+timestamp).lowercase(Locale.getDefault()).toByteArray(Charsets.UTF_8))

        redisTemplate.opsForValue().set("verification_code:$realKey",codePair[0],5,TimeUnit.MINUTES)
        return codePair[1]
    }

    override fun checkLoginParameter(entity: LoginEntity): Result {
        val realKey= "verification_code:"+DigestUtils.md5DigestAsHex((entity.code.lowercase(Locale.getDefault()) + entity.timestamp).toByteArray(Charsets.UTF_8))
        if(redisTemplate.hasKey(realKey))
        {
            redisTemplate.delete(realKey)
            val reqUrl = AppResourceConfig.oauthService+AppResourceConfig.queryTokenUri+"client_id="+AppResourceConfig.appResourcesId+"&client_secret="+
                    AppResourceConfig.appQuerySecret+"&grant_type=password&username="+entity.username+"&password="+entity.password

            val responseBody: ResponseEntity<String>
            try{
                responseBody = restTemplate.postForEntity(reqUrl,null,String::class.java)
            }catch (e:Exception){
                return Result.error(ResultStatusCode.AUTHENTICATION_CONNECT_ERR)
            }

            if(responseBody.body == null || responseBody.body!!.contains("error"))
                return  Result.error(ResultStatusCode.AUTHENTICATION_ERR)
            if(responseBody.body!!.contains("access_token"))
            {
                val regex = Regex("\\{.*?}")
                val tokenString = (regex.matchEntire(responseBody.body.toString()))!!.groupValues[0]
                val token = JSON.parseObject(tokenString, Token::class.java)
                return Result.ok(token)
            }
        }
        return Result.error(ResultStatusCode.VERIFICATIONCODE_ERROR)
    }

}