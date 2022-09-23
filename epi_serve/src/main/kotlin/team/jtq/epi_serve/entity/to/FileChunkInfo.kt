package team.jtq.epi_serve.entity.to

import org.springframework.web.multipart.MultipartFile
import team.jtq.epi_serve.annotation.NoArg

@NoArg
data class FileChunkInfo(
    val fid: String,
    val chunkNumber: Int,
    val chunkSize: Long,
    val currentChunkSize: Long,
    val totalSize: Long,
    val identifier: String,
    val filename: String,
    val relativePath: String,
    val totalChunk: Int,
    val type: String,
    val file: MultipartFile
)
