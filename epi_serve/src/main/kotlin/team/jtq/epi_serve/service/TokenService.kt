package team.jtq.epi_serve.service

import com.alibaba.fastjson.JSONObject
import team.jtq.epi_serve.tools.Result

interface TokenService {
    fun checkToken(token: String): Result
    fun getUserInfo(token: String): JSONObject?

}