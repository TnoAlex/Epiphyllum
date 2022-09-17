package team.jtq.epi_serve.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import team.jtq.epi_serve.entity.ao.GroupUpLoadeEntity
import team.jtq.epi_serve.entity.ao.UserGroupSwapEntity
import team.jtq.epi_serve.service.UsdGroupService
import team.jtq.epi_serve.tools.Result

@Controller
class GroupController {

    @Autowired
    private lateinit var groupService: UsdGroupService


    @PostMapping("/usd/add_group")
    @ResponseBody
    fun addGroup(@RequestBody entity: GroupUpLoadeEntity): Result {
        return groupService.addGroup(entity)
    }

    @PostMapping("/usd/join_group")
    @ResponseBody
    fun joinGroup(@RequestBody entity:UserGroupSwapEntity): Result {
        return groupService.joinGroup(entity)
    }

    @PostMapping("/usd/exit_group")
    @ResponseBody
    fun exitGroup(@RequestBody entity: UserGroupSwapEntity):Result{
        return groupService.exitGroup(entity)
    }

    @PostMapping("/usd/revoked_group")
    @ResponseBody
    fun revokedGroup(@RequestBody entity: UserGroupSwapEntity):Result{
        return groupService.revokedGroup(entity)
    }

}