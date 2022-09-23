package team.jtq.epi_serve.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

@TableName("usd_race_annex")
data class UsdRaceAnnex(
    @TableId(type = IdType.ASSIGN_ID)
    var id: String,
    var uid: String,
    var raceId: String,
    var annexId: String
)
