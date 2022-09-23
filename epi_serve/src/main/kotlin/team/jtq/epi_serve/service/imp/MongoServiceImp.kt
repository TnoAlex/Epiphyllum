package team.jtq.epi_serve.service.imp

import com.mongodb.client.gridfs.GridFSBucket
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.gridfs.GridFsResource
import org.springframework.data.mongodb.gridfs.GridFsTemplate
import org.springframework.stereotype.Service
import team.jtq.epi_serve.entity.FileDocument
import team.jtq.epi_serve.service.MongoService
import java.io.InputStream
import java.util.*


@Service
class MongoServiceImp : MongoService {

    @Autowired
    private lateinit var gridFSTemplate: GridFsTemplate

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    @Autowired
    private lateinit var gridFSBucket: GridFSBucket


    override fun findByID(fid: String): FileDocument? {
        val fileDocument = mongoTemplate.findById(fid, FileDocument::class.java) ?: return null
        val query = Query().addCriteria(Criteria.where("filename").`is`(fileDocument.gridFSId))
        return try {
            val fsFile = gridFSTemplate.findOne(query)
            val stream = gridFSBucket.openDownloadStream(fsFile.id)
            if (stream.gridFSFile.length > 0) {
                val resource = GridFsResource(fsFile, stream)
                fileDocument.content = resource.inputStream.readBytes()
                fileDocument
            } else
                null
        } catch (e: Exception) {
            null
        }
    }

    private fun storeFileGridFs(file: InputStream, contentType: String): String {
        val fileid = UUID.randomUUID().toString().replace("-", "")
        gridFSTemplate.store(file, fileid, contentType)
        return fileid
    }

    override fun saveFile(fileStream: InputStream, contentType: String, file: FileDocument): String {
        val gridsId = storeFileGridFs(fileStream, contentType)
        file.gridFSId = gridsId
        mongoTemplate.save(file)
        return gridsId
    }

    override fun deleteFile(fid: String): Boolean {
        return try {
            val fileDocument = mongoTemplate.findById(fid, FileDocument::class.java) ?: return false
            var query = Query().addCriteria(Criteria.where("filename").`is`(fileDocument.gridFSId))
            gridFSTemplate.delete(query)
            query = Query().addCriteria(Criteria.where("id").`is`(fid))
            mongoTemplate.remove(query, FileDocument::class.java)
            true
        } catch (e: Exception) {
            false
        }
    }


}