package team.jtq.epi_serve.service

import com.baomidou.mybatisplus.extension.service.IService
import team.jtq.epi_serve.entity.UsdUser
import team.jtq.epi_serve.entity.ao.ModifyUserEntity
import team.jtq.epi_serve.tools.Result

interface UsdUserService : IService<UsdUser> {
    fun selectUserNotice(token: String, pageIndex: String, pageItems: String): Result
    fun markNotice(token: String, nid: String): Result
    fun getUserCommonInfo(token: String): Result
    fun getUserProtectedInfo(token: String): Result
    fun modfiyUser(token: String, entity: ModifyUserEntity): Result
    fun selectUserRaceResult(token: String, pageIndex: String, pageItems: String): Result
}