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


    //验证码
    @GetMapping("/code/{timestamp}")
    @ResponseBody
    fun vCode(@PathVariable timestamp: String): Result {
        val code = loginService.verificationCodeGeneration(timestamp)
        if(code.isEmpty())
            return Result.error(ResultStatusCode.BAD_REQUEST)
        return Result.ok(code)
    }

    //登录
    @PostMapping("/login")
    @ResponseBody
    fun login(@RequestBody entity: LoginEntity): Result {
        return loginService.checkLoginParameter(entity)
    }

    //回调（用户不可见）
    @PostMapping("/doLogin/{code}")
    @ResponseBody
    fun getUser(@PathVariable code: String): Result {
        return tokenService.checkToken(code)
    }

    //注册
    @PostMapping("/register")
    @ResponseBody
    fun register(@RequestBody entity: RegisterEntity):Result{
        return registerService.registerOnRemote(entity)
    }


    //查看消息
    @PostMapping("/usd/user/notice/{pageIndex}/{pageItems}/{token}")
    @ResponseBody
    fun selectNotice(@PathVariable token: String, @PathVariable pageIndex: String, @PathVariable pageItems: String): Result {
        return userService.selectUserNotice(token,pageIndex, pageItems)
    }

    //已读消息
    @PostMapping("/usd/user/notice/mark-notice/{nid}/{token}")
    @ResponseBody
    fun markNotice(@PathVariable nid: String, @PathVariable token: String): Result {
        return userService.markNotice(token, nid)
    }

    @PostMapping("/usd/user/notice/remove-notice/{nid}/{token}")
    @ResponseBody
    fun removeNotice(@PathVariable nid: String, @PathVariable token: String): Result {
        return userService.removeNotice(token, nid)
    }

    @PostMapping("/usd/user/notice/markall-notice/{token}")
    @ResponseBody
    fun markAllNotice(@PathVariable token: String, @RequestBody nidList: List<String>): Result {
        return userService.markAllNotice(token,nidList)
    }
    //获取用户基础信息
    @PostMapping("/usd/user/info/common/{token}")
    @ResponseBody
    fun getUserCommonInfo(@PathVariable token: String): Result {
        return userService.getUserCommonInfo(token)
    }

    //获取用户受保护信息
    @PostMapping("/usd/user/info/protected/{token}")
    @ResponseBody
    fun getUserProtectedInfo(@PathVariable token: String): Result {
        return userService.getUserProtectedInfo(token)
    }

    //用户修改资料
    @PostMapping("/usd/user/modify-user/{token}")
    @ResponseBody
    fun modifyUser(@PathVariable token: String,@RequestBody entity:ModifyUserEntity): Result {
        return userService.modfiyUser(token, entity)
    }

    //查看比赛结果
    @PostMapping("/usd/user/race/result/{pageIndex}/{pageItems}/{token}")
    @ResponseBody
    fun userRaceResult(@PathVariable token: String, @PathVariable pageIndex: String, @PathVariable pageItems: String): Result {
        return userService.selectUserRaceResult(token, pageIndex, pageItems)
    }
}