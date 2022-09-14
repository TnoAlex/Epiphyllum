package team.jtq.epi_serve.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import team.jtq.epi_serve.annotation.NoArg


@NoArg
@TableName("usd_group_post")
data class UsdGroupPost (
    @TableId(type = IdType.ASSIGN_ID)
    var id: String,
    var groupId: String,
    var postId: String,
)

