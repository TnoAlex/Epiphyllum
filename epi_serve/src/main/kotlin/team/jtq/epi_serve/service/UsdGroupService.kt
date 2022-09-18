package team.jtq.epi_serve.service

import com.baomidou.mybatisplus.extension.service.IService
import team.jtq.epi_serve.entity.UsdGroup
import team.jtq.epi_serve.entity.ao.GroupUpLoadeEntity
import team.jtq.epi_serve.entity.ao.ModifyGroupEntity
import team.jtq.epi_serve.entity.ao.UserGroupSwapEntity
import team.jtq.epi_serve.tools.Result

interface UsdGroupService : IService<UsdGroup> {

    fun addGroup(entity: GroupUpLoadeEntity): Result
    fun joinGroup(entity: UserGroupSwapEntity): Result
    fun exitGroup(entity: UserGroupSwapEntity): Result
    fun revokedGroup(entity: UserGroupSwapEntity): Result
    fun modifyGroup(entity: ModifyGroupEntity): Result
    fun selectUserJoinedGroup(token: String): Result
    fun selectUserCreatedGroup(token: String): Result
}