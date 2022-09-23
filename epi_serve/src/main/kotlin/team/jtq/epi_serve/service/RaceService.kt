package team.jtq.epi_serve.service

import com.baomidou.mybatisplus.extension.service.IService
import team.jtq.epi_serve.entity.UsdRace
import team.jtq.epi_serve.entity.ao.RaceUpLoadEntity
import team.jtq.epi_serve.entity.to.FileChunkInfo
import team.jtq.epi_serve.entity.to.TFileInfo
import team.jtq.epi_serve.tools.Result

interface RaceService : IService<UsdRace> {
    fun addRace(entity: RaceUpLoadEntity, token: String): Result
    fun selectAllRace(token: String, pageIndex: String, pageItems: String): Result
    fun registrationRace(token: String, rid: String): Result
    fun selectUserJoinedRace(token: String): Result
    fun cancelRegistration(token: String, rid: String): Result
    fun uploadAnnex(rid: String, token: String, chunk: FileChunkInfo): Result
    fun finishUpload(rid: String, token: String, fileInfo: TFileInfo): Result
    fun deleteAnnex(rid: String, token: String): Result
    fun downloadAnnexs(rid: String, token: String): Result
    fun selectUserHoldRace(token: String):Result
    fun cancelRace(token:String,rid:String):Result
}