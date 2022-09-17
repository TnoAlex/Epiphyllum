package team.jtq.epi_serve.service

import com.baomidou.mybatisplus.extension.service.IService
import team.jtq.epi_serve.entity.UsdPost
import team.jtq.epi_serve.entity.ao.PostUpLoadeEntity
import team.jtq.epi_serve.tools.Result

interface UsdPostService:IService<UsdPost> {

    fun addUserPost(entity:PostUpLoadeEntity):Result
}