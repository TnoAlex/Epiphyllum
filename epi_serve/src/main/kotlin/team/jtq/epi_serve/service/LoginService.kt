package team.jtq.epi_serve.service

import team.jtq.epi_serve.entity.LoginEntity
import team.jtq.epi_serve.tools.Result

interface LoginService{
    fun verificationCodeGeneration(timestamp:String):String
    fun checkLoginParameter(entity:LoginEntity):Result
}