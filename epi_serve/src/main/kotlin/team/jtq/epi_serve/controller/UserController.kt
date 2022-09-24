package team.jtq.epi_serve.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import team.jtq.epi_serve.entity.ao.LoginEntity
import team.jtq.epi_serve.entity.ao.ModifyUserEntity
import team.jtq.epi_serve.entity.ao.RegisterEntity
import team.jtq.epi_serve.entity.ao.ResultStatusCode
import team.jtq.epi_serve.service.LoginService
import team.jtq.epi_serve.service.RegisterService
import team.jtq.epi_serve.service.TokenService
import team.jtq.epi_serve.service.UsdUserService
import team.jtq.epi_serve.tools.Result

@Controller
class UserController {

    @Autowired
    private lateinit var loginService: LoginService
    @Autowired
    private lateinit var tokenService: TokenService
    @Autowired
    private lateinit var registerService: RegisterService
    @Autowired
    private lateinit var userService: UsdUserService

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
    @ResponseBody
    fun register(@RequestBody entity: RegisterEntity):Result{
        return registerService.registerOnRemote(entity)
    }

    @PostMapping("/usd/user/notice/{token}/{pageIndex}/{pageItems}")
    @ResponseBody
    fun selectNotice(@PathVariable token: String, @PathVariable pageIndex: String, @PathVariable pageItems: String): Result {
        return userService.selectUserNotice(token,pageIndex, pageItems)
    }

    @PostMapping("/usd/user/notice/mark-notice/{token}/{nid}")
    @ResponseBody
    fun markNotice(@PathVariable nid: String, @PathVariable token: String): Result {
        return userService.markNotice(token, nid)
    }

    @PostMapping("/usd/user/info/common/{token}")
    @ResponseBody
    fun getUserCommonInfo(@PathVariable token: String): Result {
        return userService.getUserCommonInfo(token)
    }

    @PostMapping("/usd/user/info/protected/{token}")
    @ResponseBody
    fun getUserProtectedInfo(@PathVariable token: String): Result {
        return userService.getUserProtectedInfo(token)
    }

    @PostMapping("/usd/user/modify-user/{token}")
    @ResponseBody
    fun modifyUser(@PathVariable token: String,@RequestBody entity:ModifyUserEntity): Result {
        return userService.modfiyUser(token, entity)
    }

    @PostMapping("/usd/user/race/result/{token}/{pageIndex}/{pageItems}")
    @ResponseBody
    fun userRaceResult(@PathVariable token: String, @PathVariable pageIndex: String, @PathVariable pageItems: String): Result {
        return userService.selectUserRaceResult(token, pageIndex, pageItems)
    }
}