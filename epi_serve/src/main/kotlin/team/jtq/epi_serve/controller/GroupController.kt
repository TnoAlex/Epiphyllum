package team.jtq.epi_serve.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import team.jtq.epi_serve.entity.ao.GroupUpLoadeEntity
import team.jtq.epi_serve.entity.ao.ModifyGroupEntity
import team.jtq.epi_serve.service.UsdGroupService
import team.jtq.epi_serve.tools.Result

@Controller
class GroupController {

    @Autowired
    private lateinit var groupService: UsdGroupService


    @PostMapping("/usd/group/add/{token}")
    @ResponseBody
    fun addGroup(@RequestBody entity: GroupUpLoadeEntity, @PathVariable token: String): Result {
        return groupService.addGroup(entity,token)
    }

    @PostMapping("/usd/group/join/{token}/{gid}")
    @ResponseBody
    fun joinGroup(@PathVariable gid: String, @PathVariable token: String): Result {
        return groupService.joinGroup(token,gid)
    }

    @PostMapping("/usd/group/exit/{token}/{gid}")
    @ResponseBody
    fun exitGroup(@PathVariable gid: String, @PathVariable token: String):Result{
        return groupService.exitGroup(token, gid)
    }

    @PostMapping("/usd/group/revoked/{token}/{gid}")
    @ResponseBody
    fun revokedGroup(@PathVariable gid: String, @PathVariable token: String):Result{
        return groupService.revokedGroup(token, gid)
    }

    @PostMapping("/usd/group/modify/{token}")
    @ResponseBody
    fun modifyGroup(@RequestBody entity: ModifyGroupEntity, @PathVariable token: String):Result{
        return groupService.modifyGroup(entity,token)
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

    @GetMapping("/usd/group/all_group/{token}")
    @ResponseBody
    fun selectAllGroup(@PathVariable token: String): Result {
        return groupService.selectUnJoinedGroup(token)
    }


}