package team.jtq.epi_serve.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import team.jtq.epi_serve.entity.UsdPost

@Mapper
interface UsdPostMapper:BaseMapper<UsdPost> {
}