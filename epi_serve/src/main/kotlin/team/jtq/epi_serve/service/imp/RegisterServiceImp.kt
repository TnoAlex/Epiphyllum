package team.jtq.epi_serve.service.imp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.util.Base64Utils
import org.springframework.web.client.RestTemplate
import team.jtq.epi_serve.config.AppResourceConfig
import team.jtq.epi_serve.config.BeanContext
import team.jtq.epi_serve.entity.UsdUser
import team.jtq.epi_serve.entity.ao.RegisterEntity
import team.jtq.epi_serve.entity.ao.ResultStatusCode
import team.jtq.epi_serve.mapper.UsdUserMapper
import team.jtq.epi_serve.service.RegisterService
import team.jtq.epi_serve.tools.Result
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import javax.imageio.ImageIO

@Service
class RegisterServiceImp:RegisterService {

    @Autowired
    private lateinit var restTemplate: RestTemplate

    override fun registerOnRemote(entity: RegisterEntity): Result {
        val reqUrl = AppResourceConfig.oauthService+AppResourceConfig.remoteSingup
        val responseBody: ResponseEntity<Result>
        entity.accountLevel = 2
        entity.addition = AppResourceConfig.appResourcesId
        try{
            responseBody = restTemplate.postForEntity(reqUrl,entity,Result::class.java)
            if(responseBody.body == null)
            {
                return Result.error(ResultStatusCode.UNKONW_ERROR)
            }
            val mapper = BeanContext.getBeanbyClazz(UsdUserMapper::class.java)
            val obj = UsdUser::class.java.newInstance()
            obj.uid = responseBody.body!!.data as String
            obj.nickName = entity.username
            val classResource = ClassPathResource(AppResourceConfig.defaultUserIco)
            val img = ImageIO.read(classResource.inputStream)
            val stream = ByteArrayOutputStream()
            ImageIO.write(img,"png",stream)
            val base64Img = "data:image/png;base64,"+Base64Utils.encodeToString(stream.toByteArray())
            obj.portrait =base64Img
            mapper.insert(obj)
            return Result.ok()
        }catch (e:Exception)
        {
            return Result.error(ResultStatusCode.AUTHENTICATION_CONNECT_ERR)
        }
    }
}