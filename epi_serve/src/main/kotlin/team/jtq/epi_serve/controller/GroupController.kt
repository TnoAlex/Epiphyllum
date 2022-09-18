package team.jtq.epi_serve.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import team.jtq.epi_serve.entity.ao.GroupUpLoadeEntity
import team.jtq.epi_serve.entity.ao.ModifyGroupEntity
import team.jtq.epi_serve.entity.ao.UserGroupSwapEntity
import team.jtq.epi_serve.service.UsdGroupService
import team.jtq.epi_serve.tools.Result

@Controller
class GroupController {

    @Autowired
    private lateinit var groupService: UsdGroupService


    @PostMapping("/usd/group/add")
    @ResponseBody
    fun addGroup(@RequestBody entity: GroupUpLoadeEntity): Result {
        return groupService.addGroup(entity)
    }

    @PostMapping("/usd/group/join")
    @ResponseBody
    fun joinGroup(@RequestBody entity:UserGroupSwapEntity): Result {
        return groupService.joinGroup(entity)
    }

    @PostMapping("/usd/group/exit")
    @ResponseBody
    fun exitGroup(@RequestBody entity: UserGroupSwapEntity):Result{
        return groupService.exitGroup(entity)
    }

    @PostMapping("/usd/group/revoked")
    @ResponseBody
    fun revokedGroup(@RequestBody entity: UserGroupSwapEntity):Result{
        return groupService.revokedGroup(entity)
    }

    @PostMapping("/usd/group/modify")
    @ResponseBody
    fun modifyGroup(@RequestBody entity: ModifyGroupEntity):Result{
        return groupService.modifyGroup(entity)
    }

    @GetMapping("/usd/group/user_joined/{token}")
    @ResponseBody
    fun selectUserJoinedGroup(@PathVariable token: String): Result {
        return groupService.selectUserJoinedGroup(token)
    }

    @GetMapping("/usd/group/user_created/{token}")
    @ResponseBody
    fun selectUserCreatedGroup(@PathVariable token: String): Result {
        return groupService.selectUserCreatedGroup(token)
    }

}