package team.jtq.epi_serve.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import team.jtq.epi_serve.annotation.NoArg

@NoArg
@TableName("usd_user_favorites")
open class UsdUserFavorites (
    @TableId(type = IdType.ASSIGN_ID)
    var id: String,
    var postId: String,
    var uid: String

)
