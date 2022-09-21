package team.jtq.epi_serve.service

import com.baomidou.mybatisplus.extension.service.IService
import team.jtq.epi_serve.entity.UsdGroup
import team.jtq.epi_serve.entity.ao.GroupUpLoadeEntity
import team.jtq.epi_serve.entity.ao.ModifyGroupEntity
import team.jtq.epi_serve.tools.Result

interface UsdGroupService : IService<UsdGroup> {

    fun addGroup(entity: GroupUpLoadeEntity, token: String): Result
    fun joinGroup(token: String, gid: String): Result
    fun exitGroup(token: String, gid: String): Result
    fun revokedGroup(token: String, gid: String): Result
    fun modifyGroup(entity: ModifyGroupEntity, token: String): Result
    fun selectUserJoinedGroup(token: String): Result
    fun selectUserCreatedGroup(token: String): Result

    fun selectUnJoinedGroup(token: String): Result
}