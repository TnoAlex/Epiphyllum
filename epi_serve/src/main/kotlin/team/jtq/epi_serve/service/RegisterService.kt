package team.jtq.epi_serve.service

import team.jtq.epi_serve.entity.ao.RegisterEntity
import team.jtq.epi_serve.tools.Result

interface RegisterService {
    fun registerOnRemote(entity: RegisterEntity): Result
}