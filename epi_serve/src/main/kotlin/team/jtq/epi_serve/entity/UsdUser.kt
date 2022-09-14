package team.jtq.epi_serve.entity

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import team.jtq.epi_serve.annotation.NoArg

@NoArg
@TableName("usd_user")
data class UsdUser(
    @TableId
    val uid:String,
    val signature:String,
    val occupation:String,
    val portrait:String,
)
