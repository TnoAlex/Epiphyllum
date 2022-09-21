package team.jtq.epi_serve.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import team.jtq.epi_serve.entity.ao.RaceUpLoadEntity
import team.jtq.epi_serve.service.RaceService
import team.jtq.epi_serve.tools.Result

@Controller
class RaceController {

    @Autowired
    private lateinit var raceService: RaceService

    @PostMapping("/usd/race/add-race/{token}")
    @ResponseBody
    fun addRace(@PathVariable token: String,@RequestBody entity:RaceUpLoadEntity): Result {
        return raceService.addRace(entity,token)
    }

    @GetMapping("/usd/race/all-race/{token}/{pageIndex}/{pageItems}")
    @ResponseBody
    fun selectAllRace(@PathVariable token: String, @PathVariable pageIndex: String, @PathVariable pageItems: String): Result {
        return raceService.selectAllRace(token,pageIndex, pageItems)
    }

    @PostMapping("/race/registration/{token}/{rid}")
    @ResponseBody
    fun registrationRace(@PathVariable rid: String, @PathVariable token: String): Result {
        return raceService.registrationRace(token, rid)
    }

}