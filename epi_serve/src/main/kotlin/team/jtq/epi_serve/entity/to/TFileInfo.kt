package team.jtq.epi_serve.entity.to

import team.jtq.epi_serve.annotation.NoArg

@NoArg
data class TFileInfo(
    val id: String,
    //附件类型
    val fileType: String,
    //附件名称
    val name: String,
    //附件总大小
    val size: Long,
    //附件地址
    val relativePath: String,
    //附件MD5标识
    val uniqueIdentifier: String,
)
