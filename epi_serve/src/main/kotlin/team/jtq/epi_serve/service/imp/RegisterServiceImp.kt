package team.jtq.epi_serve.service.imp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import team.jtq.epi_serve.config.AppResourceConfig
import team.jtq.epi_serve.entity.RegisterEntity
import team.jtq.epi_serve.entity.ResultStatusCode
import team.jtq.epi_serve.service.RegisterService
import team.jtq.epi_serve.tools.Result

@Service
class RegisterServiceImp:RegisterService {

    @Autowired
    private lateinit var restTemplate: RestTemplate

    override fun registerOnRemote(entity: RegisterEntity): Result {
        val reqUrl = AppResourceConfig.oauthService+AppResourceConfig.remoteSingup
        entity.addition = AppResourceConfig.appResourcesId
        val responseBody: ResponseEntity<Result>
        try{
            responseBody = restTemplate.postForEntity(reqUrl,entity,Result::class.java)
            if(responseBody.body == null)
            {
                return Result.error(ResultStatusCode.UNKONW_ERROR)
            }
            return responseBody.body!!
        }catch (e:Exception)
        {
            return Result.error(ResultStatusCode.AUTHENTICATION_CONNECT_ERR)
        }
    }
}