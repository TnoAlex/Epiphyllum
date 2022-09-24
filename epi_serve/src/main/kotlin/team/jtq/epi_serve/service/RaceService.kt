package team.jtq.epi_serve.service

import com.baomidou.mybatisplus.extension.service.IService
import org.springframework.web.multipart.MultipartFile
import team.jtq.epi_serve.entity.UsdRace
import team.jtq.epi_serve.entity.ao.RaceUpLoadEntity
import team.jtq.epi_serve.entity.to.FileChunkInfo
import team.jtq.epi_serve.entity.to.TFileInfo
import team.jtq.epi_serve.tools.Result
import javax.servlet.http.HttpServletResponse

interface RaceService : IService<UsdRace> {
    fun addRace(entity: RaceUpLoadEntity, token: String): Result
    fun selectAllRace(token: String, pageIndex: String, pageItems: String): Result
    fun registrationRace(token: String, rid: String): Result
    fun selectUserJoinedRace(token: String): Result
    fun cancelRegistration(token: String, rid: String): Result
    fun uploadAnnex(rid: String, token: String, chunk: FileChunkInfo): Result
    fun finishUpload(rid: String, token: String, fileInfo: TFileInfo): Result
    fun deleteAnnex(rid: String, token: String): Result
    fun downloadAnnexList(rid: String, token: String, pageIndex: String, pageItems: String): Result
    fun selectUserHoldRace(token: String): Result
    fun cancelRace(token: String, rid: String): Result
    fun selectRaceInfo(token: String, rid: String): Result
    fun noticeEntrants(token: String, rid: String): Result
    fun downloadAnnex(fid: String, rid: String, token: String, response: HttpServletResponse): Result
    fun getAnnexExcel(rid: String, token: String, response: HttpServletResponse): Result
    fun uploadExcel(rid: String, token: String, file: MultipartFile): Result
}