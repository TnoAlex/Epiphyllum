package team.jtq.epi_serve.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import team.jtq.epi_serve.annotation.NoArg

@NoArg
@Document
data class FileDocument(
    @Id
    var id: String,
    val fileName: String,
    val fileSize: Long,
    val createTime: String,
    val md5: String,
    var content: ByteArray?,
    val contentType: String,
    val linkType:String,
    val linkId:Pair<String,String>,
    var gridFSId:String?
)
