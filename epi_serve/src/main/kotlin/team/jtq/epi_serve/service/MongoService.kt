package team.jtq.epi_serve.service

import team.jtq.epi_serve.entity.FileDocument
import java.io.InputStream

interface MongoService {
    fun findByID(fid:String): FileDocument?
    fun saveFile(fileStream: InputStream, contentType: String, file: FileDocument): String
    fun deleteFile(fid:String):Boolean
}