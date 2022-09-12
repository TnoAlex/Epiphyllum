package team.jtq.auth.oauth_serve.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import team.jtq.auth.oauth_serve.entity.RegisterEntity
import team.jtq.auth.oauth_serve.service.OauthUserDetailService
import team.jtq.auth.oauth_serve.service.imp.OauthUserDetailServiceImp
import team.jtq.auth.oauth_serve.tools.Result

@Controller
class SecurityController {

    @Autowired
    private lateinit var userDetailService: OauthUserDetailService

    @PostMapping("/oauth/signup")
    @ResponseBody
    fun register(@RequestBody entity: RegisterEntity):Result{
        val res = userDetailService.addAccount(entity)
        return if(res){
            Result.ok()
        } else
            Result.error("注册失败!")
    }

    @RequestMapping("/oauth/confirm_account/{uid}/{confirm_code}")
    @ResponseBody
    fun confirmRegister(@PathVariable confirm_code: String, @PathVariable uid: String){
        userDetailService.verifyConfirmationCode(confirm_code,uid)
    }
}