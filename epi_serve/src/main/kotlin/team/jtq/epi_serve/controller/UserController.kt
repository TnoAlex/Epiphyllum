package team.jtq.epi_serve.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import team.jtq.epi_serve.entity.ao.LoginEntity
import team.jtq.epi_serve.entity.ao.RegisterEntity
import team.jtq.epi_serve.entity.ao.ResultStatusCode
import team.jtq.epi_serve.service.LoginService
import team.jtq.epi_serve.service.RegisterService
import team.jtq.epi_serve.service.TokenService
import team.jtq.epi_serve.tools.Result

@Controller
class UserController {

    @Autowired
    private lateinit var loginService: LoginService
    @Autowired
    private lateinit var tokenService: TokenService
    @Autowired
    private lateinit var registerService: RegisterService

    @GetMapping("/code/{timestamp}")
    @ResponseBody
    fun vCode(@PathVariable timestamp: String): Result {
        val code = loginService.verificationCodeGeneration(timestamp)
        if(code.isEmpty())
            return Result.error(ResultStatusCode.BAD_REQUEST)
        return Result.ok(code)
    }

    @PostMapping("/login")
    @ResponseBody
    fun login(@RequestBody entity: LoginEntity): Result {
        return loginService.checkLoginParameter(entity)
    }
    @PostMapping("/doLogin/{code}")
    @ResponseBody
    fun getUser(@PathVariable code: String):Result{
        return tokenService.checkToken(code)
    }
    @PostMapping("/register")
    fun register(@RequestBody entity: RegisterEntity):Result{
        return registerService.registerOnRemote(entity)
    }
}