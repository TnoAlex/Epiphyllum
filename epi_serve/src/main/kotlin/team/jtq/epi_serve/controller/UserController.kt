package team.jtq.epi_serve.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import team.jtq.epi_serve.entity.LoginEntity
import team.jtq.epi_serve.entity.RegisterEntity
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

    @PostMapping("/code/{timestamp}")
    @ResponseBody
    fun vCode(@PathVariable timestamp: String): Result {
        return Result.ok(loginService.verificationCodeGeneration(timestamp))
    }

    @PostMapping("/login")
    @ResponseBody
    fun login(@RequestBody entity: LoginEntity): Result {
        return loginService.checkLoginParameter(entity)
    }
    @PostMapping("/doLogin/{code}")
    @ResponseBody
    fun getUser(@PathVariable code: String):Result{
        return tokenService.getUserParameter(code)
    }
    @PostMapping("/register")
    fun register(@RequestBody entity:RegisterEntity):Result{
        return registerService.registerOnRemote(entity)
    }

}