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

    //新建竞赛
    @PostMapping("/usd/race/add-race/{token}")
    @ResponseBody
    fun addRace(@PathVariable token: String, @RequestBody entity: RaceUpLoadEntity): Result {
        return raceService.addRace(entity, token)
    }

    //查看所有竞赛
    @GetMapping("/usd/race/all-race/{token}/{pageIndex}/{pageItems}")
    @ResponseBody
    fun selectAllRace(
        @PathVariable token: String,
        @PathVariable pageIndex: String,
        @PathVariable pageItems: String
    ): Result {
        return raceService.selectAllRace(token, pageIndex, pageItems)
    }

    //报名比赛
    @PostMapping("/race/registration/{token}/{rid}")
    @ResponseBody
    fun registrationRace(@PathVariable rid: String, @PathVariable token: String): Result {
        return raceService.registrationRace(token, rid)
    }

    //查看已经报名的比赛
    @PostMapping("/race/user/joined-race/{token}")
    @ResponseBody
    fun selectUserJoinedRace(@PathVariable token: String): Result {
        return raceService.selectUserJoinedRace(token)
    }

    //取消报名比赛
    @PostMapping("/race/user/cancel-registration/{token}/{rid}")
    @ResponseBody
    fun cancelRegistration(@PathVariable rid: String, @PathVariable token: String): Result {
        return raceService.cancelRegistration(token, rid)
    }

    //上传比赛附件
    @PostMapping("/usd/user/upload-annex/{token}/{rid}")
    @ResponseBody
    fun uploadAnnex(@PathVariable rid: String, @PathVariable token: String, chunk: FileChunkInfo): Result {
        return raceService.uploadAnnex(rid, token, chunk)
    }

    //回调（不对用户展示）
    @PostMapping("/usd/user/finish-upload/{token}/{rid}")
    @ResponseBody
    fun finishUpload(@PathVariable rid: String, @PathVariable token: String, @RequestBody fileInfo: TFileInfo): Result {
        return raceService.finishUpload(rid, token, fileInfo)
    }

    //删除附件
    @PostMapping("/usd/user/delete-annex/{token}/{rid}")
    @ResponseBody
    fun deleteAnnex(@PathVariable rid: String, @PathVariable token: String): Result {
        return raceService.deleteAnnex(rid, token)
    }

    //查看附件列表（机构）
    @PostMapping("/usd/race/download-annex-list/{token}/{rid}/{pageIndex}/{pageItems}")
    @ResponseBody
    fun downloadAnnexList(
        @PathVariable rid: String, @PathVariable token: String, @PathVariable pageIndex: String,
        @PathVariable pageItems: String
    ): Result {
        return raceService.downloadAnnexList(rid, token, pageIndex, pageItems)
    }

    //查看自己发起的比赛
    @PostMapping("/usd/race/org-hold/{token}")
    @ResponseBody
    fun selectUserHoldRace(@PathVariable token: String): Result {
        return raceService.selectUserHoldRace(token)
    }

    //取消已经发布的比赛
    @PostMapping("/usd/race/cancel-race/{token}/{rid}")
    @ResponseBody
    fun cancelRace(@PathVariable rid: String, @PathVariable token: String): Result {
        return raceService.cancelRace(token, rid)
    }

    //查看比赛报名和提交信息
    @PostMapping("/usd/race/race-info/{token}/{rid}")
    @ResponseBody
    fun raceInfo(@PathVariable rid: String, @PathVariable token: String): Result {
        return raceService.selectRaceInfo(token, rid)
    }

    //提醒参赛者提交产物
    @PostMapping("/usd/race/notice-entrants/{token}/{rid}")
    @ResponseBody
    fun noticeEntrants(@PathVariable rid: String, @PathVariable token: String): Result {
        return raceService.noticeEntrants(token, rid)
    }

    //获取参赛者列表（excel下载）
    @PostMapping("/usd/race/entrants/{token}/{rid}")
    @ResponseBody
    fun getEntrantsExcel(@PathVariable rid: String, @PathVariable token: String): Result {
        return raceService.getAnnexExcel(rid, token, response)
    }

    //上传获奖情况
    @PostMapping("/usd/race/race-info/upload-awards/{token}/{rid}")
    @ResponseBody
    fun uploadExcel(@PathVariable rid: String, @PathVariable token: String, file: MultipartFile): Result {
        return raceService.uploadExcel(rid, token, file)
    }

}