package team.jtq.auth.oauth_serve.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import team.jtq.auth.oauth_serve.entity.OauthCode

@Mapper
interface OauthCodeMapper:BaseMapper<OauthCode> {
}