package team.jtq.auth.oauth_serve.controller

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import team.jtq.auth.oauth_serve.tools.MD5Encode
import java.util.*
import team.jtq.auth.oauth_serve.tools.Result
import team.jtq.auth.oauth_serve.tools.generate
import team.jtq.auth.oauth_serve.tools.randomGen
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.annotation.Resource


@Controller
class SecurityController {
    @Resource
    private lateinit var redisTemplate: RedisTemplate<String, String>

    @RequestMapping("/index")
    fun index(): String {
        return "index"
    }

    @RequestMapping("/login")
    fun login(): String {
        return "login"
    }

    @RequestMapping("oauthTest")
    fun oauthTest(): String {
        return "oauthTest"
    }

    @RequestMapping("resourceTest")
    fun resourceTest(): String {
        return "resourceTest"
    }

    @GetMapping("/code/{key}")
    @ResponseBody
    fun getCode(@PathVariable key: String): Result {
        val code: String = randomGen(4)
        val lowerCaseCode = code.lowercase(Locale.getDefault())
        val realKey= MD5Encode(lowerCaseCode + key, "utf-8")
        redisTemplate.opsForValue()[realKey, code, 5] = TimeUnit.MINUTES
        val base64: String = generate(code)
//        Result.ok(base64)
        return Result.ok(code)
    }
}