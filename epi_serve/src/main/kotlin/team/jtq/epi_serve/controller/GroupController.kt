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

    //添加群组
    @PostMapping("/usd/group/add/{token}")
    @ResponseBody
    fun addGroup(@RequestBody entity: GroupUpLoadeEntity, @PathVariable token: String): Result {
        return groupService.addGroup(entity,token)
    }

    //加入群组
    @PostMapping("/usd/group/join/{gid}/{token}")
    @ResponseBody
    fun joinGroup(@PathVariable gid: String, @PathVariable token: String): Result {
        return groupService.joinGroup(token,gid)
    }

    //退出群组
    @PostMapping("/usd/group/exit/{gid}/{token}")
    @ResponseBody
    fun exitGroup(@PathVariable gid: String, @PathVariable token: String):Result{
        return groupService.exitGroup(token, gid)
    }

    //注销群组
    @PostMapping("/usd/group/revoked/{gid}/{token}")
    @ResponseBody
    fun revokedGroup(@PathVariable gid: String, @PathVariable token: String):Result{
        return groupService.revokedGroup(token, gid)
    }

    //修改群组
    @PostMapping("/usd/group/modify/{token}")
    @ResponseBody
    fun modifyGroup(@RequestBody entity: ModifyGroupEntity, @PathVariable token: String):Result{
        return groupService.modifyGroup(entity,token)
    }

    //查找用户加入的群组
    @GetMapping("/usd/group/user_joined/{token}")
    @ResponseBody
    fun selectUserJoinedGroup(@PathVariable token: String): Result {
        return groupService.selectUserJoinedGroup(token)
    }

    //查找用户创建的群组
    @GetMapping("/usd/group/user_created/{token}")
    @ResponseBody
    fun selectUserCreatedGroup(@PathVariable token: String): Result {
        return groupService.selectUserCreatedGroup(token)
    }

    //查看所有群组
    @GetMapping("/usd/group/all_group/{token}")
    @ResponseBody
    fun selectAllGroup(@PathVariable token: String): Result {
        return groupService.selectUnJoinedGroup(token)
    }


}