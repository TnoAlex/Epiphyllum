package team.jtq.epi_serve.entity

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import team.jtq.epi_serve.annotation.NoArg

@NoArg
@TableName("usd_user_race")
data class UsdUserRace (
    @TableId
    var id: String,
    var uid: String,
    var raceId: String
)
