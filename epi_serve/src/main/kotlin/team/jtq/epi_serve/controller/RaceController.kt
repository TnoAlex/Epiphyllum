package team.jtq.epi_serve.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import team.jtq.epi_serve.entity.ao.RaceUpLoadEntity
import team.jtq.epi_serve.entity.to.FileChunkInfo
import team.jtq.epi_serve.entity.to.TFileInfo
import team.jtq.epi_serve.service.RaceService
import team.jtq.epi_serve.tools.Result
import javax.servlet.http.HttpServletResponse

@Controller
class RaceController {

    @Autowired
    private lateinit var raceService: RaceService

    @Autowired
    private lateinit var response: HttpServletResponse

    @PostMapping("/usd/race/add-race/{token}")
    @ResponseBody
    fun addRace(@PathVariable token: String, @RequestBody entity: RaceUpLoadEntity): Result {
        return raceService.addRace(entity, token)
    }

    @GetMapping("/usd/race/all-race/{token}/{pageIndex}/{pageItems}")
    @ResponseBody
    fun selectAllRace(
        @PathVariable token: String,
        @PathVariable pageIndex: String,
        @PathVariable pageItems: String
    ): Result {
        return raceService.selectAllRace(token, pageIndex, pageItems)
    }

    @PostMapping("/race/registration/{token}/{rid}")
    @ResponseBody
    fun registrationRace(@PathVariable rid: String, @PathVariable token: String): Result {
        return raceService.registrationRace(token, rid)
    }

    @PostMapping("/race/user/joined-race/{token}")
    @ResponseBody
    fun selectUserJoinedRace(@PathVariable token: String): Result {
        return raceService.selectUserJoinedRace(token)
    }

    @PostMapping("/race/user/cancel-registration/{token}/{rid}")
    @ResponseBody
    fun cancelRegistration(@PathVariable rid: String, @PathVariable token: String): Result {
        return raceService.cancelRegistration(token, rid)
    }

    @PostMapping("/usd/user/upload-annex/{token}/{rid}")
    @ResponseBody
    fun uploadAnnex(@PathVariable rid: String, @PathVariable token: String, chunk: FileChunkInfo): Result {
        return raceService.uploadAnnex(rid, token, chunk)
    }

    @PostMapping("/usd/user/finish-upload/{token}/{rid}")
    @ResponseBody
    fun finishUpload(@PathVariable rid: String, @PathVariable token: String, @RequestBody fileInfo: TFileInfo): Result {
        return raceService.finishUpload(rid, token, fileInfo)
    }

    @PostMapping("/usd/user/delete-annex/{token}/{rid}")
    @ResponseBody
    fun deleteAnnex(@PathVariable rid: String, @PathVariable token: String): Result {
        return raceService.deleteAnnex(rid, token)
    }

    @PostMapping("/usd/race/download-annex-list/{token}/{rid}/{pageIndex}/{pageItems}")
    @ResponseBody
    fun downloadAnnexList(
        @PathVariable rid: String, @PathVariable token: String, @PathVariable pageIndex: String,
        @PathVariable pageItems: String
    ): Result {
        return raceService.downloadAnnexList(rid, token, pageIndex, pageItems)
    }

    @PostMapping("/usd/race/org-hold/{token}")
    @ResponseBody
    fun selectUserHoldRace(@PathVariable token: String): Result {
        return raceService.selectUserHoldRace(token)
    }

    @PostMapping("/usd/race/cancel-race/{token}/{rid}")
    @ResponseBody
    fun cancelRace(@PathVariable rid: String, @PathVariable token: String): Result {
        return raceService.cancelRace(token, rid)
    }

    @PostMapping("/usd/race/race-info/{token}/{rid}")
    @ResponseBody
    fun raceInfo(@PathVariable rid: String, @PathVariable token: String): Result {
        return raceService.selectRaceInfo(token, rid)
    }

    @PostMapping("/usd/race/notice-entrants/{token}/{rid}")
    @ResponseBody
    fun noticeEntrants(@PathVariable rid: String, @PathVariable token: String): Result {
        return raceService.noticeEntrants(token, rid)
    }

    @PostMapping("/usd/race/entrants/{token}/{rid}")
    @ResponseBody
    fun getEntrantsExcel(@PathVariable rid: String, @PathVariable token: String): Result {
        return raceService.getAnnexExcel(rid, token, response)
    }

    @PostMapping("/usd/race/race-info/upload-awards/{token}/{rid}")
    @ResponseBody
    fun uploadExcel(@PathVariable rid: String, @PathVariable token: String, file: MultipartFile): Result {
        return raceService.uploadExcel(rid, token, file)
    }

}