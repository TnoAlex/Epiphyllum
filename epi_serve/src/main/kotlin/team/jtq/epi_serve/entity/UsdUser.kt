package team.jtq.epi_serve.entity

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import team.jtq.epi_serve.annotation.NoArg

@NoArg
@TableName("usd_user")
data class UsdUser(
    @TableId
    var id:String,
    var uid:String,
    var signature:String,
    var occupation:String,
    var portrait:String,
    var nickName:String
)
