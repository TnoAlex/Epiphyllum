package team.jtq.epi_serve.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import team.jtq.epi_serve.entity.LoginEntity
import team.jtq.epi_serve.entity.ResultStatusCode
import team.jtq.epi_serve.service.LoginService
import team.jtq.epi_serve.tools.Result

@Controller
class LoginController {
    @Autowired
    private lateinit var loginService: LoginService

    @PostMapping("/code/{timestamp}")
    @ResponseBody
    fun vCode(@PathVariable timestamp: String): Result {
        return Result.ok(loginService.verificationCodeGeneration(timestamp))
    }

    @PostMapping("/login")
    @ResponseBody
    fun login(@RequestBody entity: LoginEntity):Result{
        val codeResult = loginService.checkLoginParameter(entity)
        return codeResult
    }

}