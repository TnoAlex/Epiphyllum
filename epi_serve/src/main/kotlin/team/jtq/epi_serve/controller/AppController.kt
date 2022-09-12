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
import team.jtq.epi_serve.tools.Result

@Controller
class AppController {
    @Autowired
    private lateinit var loginService: LoginService

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
    fun getUser(@PathVariable code: String):Result{
        return loginService.getUserParameter(code)
    }

    fun register(@RequestBody entity:RegisterEntity):Result{
        TODO()

    }

}