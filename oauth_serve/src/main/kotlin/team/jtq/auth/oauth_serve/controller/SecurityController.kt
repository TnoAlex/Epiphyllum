package team.jtq.auth.oauth_serve.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import team.jtq.auth.oauth_serve.entity.RegisterEntity
import team.jtq.auth.oauth_serve.service.imp.OauthUserDetailServiceImp
import team.jtq.auth.oauth_serve.tools.Result

@Controller
class SecurityController {

    @Autowired
    private lateinit var userDetailServiceImp: OauthUserDetailServiceImp

    @PostMapping("/oauth/signup")
    @ResponseBody
    fun register(@RequestBody entity: RegisterEntity):Result{
        TODO()
    }
}