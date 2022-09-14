package team.jtq.epi_serve.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import team.jtq.epi_serve.annotation.NoArg
import java.time.LocalDateTime

@NoArg
@TableName("usd_post")
open class UsdPost (
    @TableId(type = IdType.ASSIGN_ID)
    var id: String,
    var postContent: String,
    var createTime: LocalDateTime,
    var status: Int,
    var likes: Int,
    var favorites: Int
)

