package team.jtq.epi_serve.service.imp

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.util.Base64Utils
import org.springframework.util.DigestUtils
import org.springframework.web.client.RestTemplate
import team.jtq.epi_serve.config.AppResourceConfig
import team.jtq.epi_serve.entity.ao.ResultStatusCode
import team.jtq.epi_serve.service.TokenService
import team.jtq.epi_serve.tools.Result
import java.io.BufferedReader
import java.io.IOException
import java.security.KeyFactory
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*
import java.util.concurrent.TimeUnit
import javax.annotation.Resource
import javax.crypto.Cipher

@Service
class TokenServiceImp : TokenService {

    private val TOKEN_PREFIX = "APPCACHE_TOKEN"

    @Autowired
    private lateinit var restTemplate: RestTemplate

    @Resource
    private lateinit var redisTemplate: RedisTemplate<String,Any>

    override fun checkToken(token: String): Result {
        if (token.isEmpty())
            return Result.error(ResultStatusCode.TOKEN_MISS)
        val redisKey  = TOKEN_PREFIX +":"+ DigestUtils.md5DigestAsHex(token.toByteArray(Charsets.UTF_8))
        val encryptList = listOf("user_account","user_name","user_gender","user_phone","user_status","user_id","authorities","user_identification")
        if(redisTemplate.hasKey(redisKey)){
            val obj = redisTemplate.opsForValue().get(redisKey)
            val json = JSON.parseObject(JSON.toJSONString(obj))
            val res = JSONObject()
            encryptList.forEach { i->res[i] = json[i] }
            return Result.ok(res)
        }
        val responseBody: ResponseEntity<String>
        val reqUrl = AppResourceConfig.oauthService + AppResourceConfig.checkTokenUri + "token=" + token
        try {
            responseBody = restTemplate.postForEntity(reqUrl, null, String::class.java)
        } catch (e: Exception) {
            return Result.error(ResultStatusCode.AUTHENTICATION_CONNECT_ERR)
        }
        if (responseBody.body == null)
            return Result.error(ResultStatusCode.INVALID_TOKEN)
        if (responseBody.body!!.contains("error")) {
            return Result.error(ResultStatusCode.INVALID_TOKEN)
        }
        if (responseBody.body!!.contains("client_id")) {
            val responseRes = JSON.parseObject(responseBody.body)

            val encryptItems = JSONObject()
            encryptList.forEach { i->encryptItems[i]=responseRes[i] }
            val json = rsaDecode(encryptItems) ?: return Result.error(ResultStatusCode.SERVICE_INNER_ERR)
            encryptList.forEach { i->responseRes[i] = json[i] }

            redisTemplate.opsForValue().set(redisKey,responseRes,1,TimeUnit.DAYS)
            return Result.ok(json)
        }
        return Result.error(ResultStatusCode.UNKONW_ERROR)
    }
    override fun getUserInfo(token: String): JSONObject? {
        val redisKey = TOKEN_PREFIX+":" + DigestUtils.md5DigestAsHex(token.toByteArray(Charsets.UTF_8))
        if (redisTemplate.hasKey(redisKey)) {
            val obj = redisTemplate.opsForValue().get(redisKey)
            return JSON.parseObject(JSON.toJSONString(obj))
        }
        return null
    }

    private fun rsaDecode(json: JSONObject): JSONObject? {
        val classResource = ClassPathResource(AppResourceConfig.RSAPath)
        val builder = StringBuilder()
        try {
            val fileInputStream = classResource.inputStream
            val reader = BufferedReader(fileInputStream.reader(Charsets.UTF_8))
            reader.use { r ->
                r.lineSequence().forEach {
                    builder.append(it)
                }
            }
        } catch (e: IOException) {
            return null
        }
        val key = builder.toString().replace("\r\n", "")
        val decodeKey = Base64Utils.decodeFromString(key)
        val pkcs8KeySpec = PKCS8EncodedKeySpec(decodeKey)
        val keyFactory = KeyFactory.getInstance("RSA")
        val privateKey = keyFactory.generatePrivate(pkcs8KeySpec)
        val cipher = Cipher.getInstance(keyFactory.algorithm)
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        val res = JSONObject()
        for ((k, v) in json) {
            val dsv = getMaxResultDecrypt(JSONObject.parseObject(JSON.toJSONString(v), ByteArray::class.java), cipher) ?: return null
            res[k] = String(dsv)
        }
        return res
    }

    private fun getMaxResultDecrypt(inputArray: ByteArray, cipher: Cipher): ByteArray? {
        val inputLength = inputArray.size
        // 最大解密字节数，超出最大字节数需要分组加密
        val MAX_ENCRYPT_BLOCK = 128
        // 标识
        var offSet = 0
        var resultBytes = byteArrayOf()
        var cache: ByteArray
        try {
            while (inputLength - offSet > 0) {
                if (inputLength - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(inputArray, offSet, MAX_ENCRYPT_BLOCK)
                    offSet += MAX_ENCRYPT_BLOCK
                } else {
                    cache = cipher.doFinal(inputArray, offSet, inputLength - offSet)
                    offSet = inputLength
                }
                resultBytes = Arrays.copyOf(resultBytes, resultBytes.size + cache.size)
                System.arraycopy(cache, 0, resultBytes, resultBytes.size - cache.size, cache.size)
            }
            return resultBytes
        } catch (e: Exception) {
            return null
        }
    }
}