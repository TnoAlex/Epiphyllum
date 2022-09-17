package team.jtq.epi_serve.entity.ao

import com.alibaba.fastjson.annotation.JSONCreator
import com.alibaba.fastjson.annotation.JSONField
import team.jtq.epi_serve.annotation.NoArg

@NoArg
data class Token @JSONCreator constructor(
    @JSONField(name = "access_token")
    var accessToken:String,
    @JSONField(name="token_type")
    var tokenType:String,
    @JSONField(name = "refresh_token")
    var refreshToken:String,
    @JSONField(name = "expires_in")
    var survivalTime:String,
    var scope:String
)