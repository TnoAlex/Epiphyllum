package team.jtq.epi_serve.config

import com.mongodb.client.MongoClient
import com.mongodb.client.gridfs.GridFSBucket
import com.mongodb.client.gridfs.GridFSBuckets
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class MongoDBConfig {

    @Value("\${spring.data.mongodb.database}")
    private lateinit var db:String

    @Bean
    fun getGridFSBucket(mongoClient: MongoClient): GridFSBucket? {
        val mongoDatabase = mongoClient.getDatabase(db)
        return GridFSBuckets.create(mongoDatabase)
    }
}