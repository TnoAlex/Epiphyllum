package team.jtq.epi_serve.service

import team.jtq.epi_serve.tools.Result

interface TokenService {
    fun getUserParameter(token:String): Result
}