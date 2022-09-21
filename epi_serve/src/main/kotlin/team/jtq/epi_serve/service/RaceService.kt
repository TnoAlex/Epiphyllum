package team.jtq.epi_serve.service

import com.baomidou.mybatisplus.extension.service.IService
import team.jtq.epi_serve.entity.UsdRace
import team.jtq.epi_serve.entity.ao.RaceUpLoadEntity
import team.jtq.epi_serve.tools.Result

interface RaceService : IService<UsdRace> {
    fun addRace(entity: RaceUpLoadEntity, token: String): Result
    fun selectAllRace(token: String, pageIndex: String, pageItems: String): Result
    fun registrationRace(token: String, rid: String):Result
}