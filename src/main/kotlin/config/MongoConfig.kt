package org.example.config

import com.mongodb.*
import com.mongodb.client.MongoClients
import exception.CustomException
import exception.ErrorCode
import model.enums.MongoTableCollector
import org.bson.UuidRepresentation
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import java.util.*

@Configuration
@EnableMongoAuditing
class MongoConfig {

    @Value("\${spring.data.mongo.uri}")
    private val uri: String? = null

    @Bean
    fun template(): Map<MongoTableCollector, MongoTemplate> {
        val mapper = EnumMap<MongoTableCollector, MongoTemplate>(MongoTableCollector::class.java)
        val serverApi = ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build()

        for (c in MongoTableCollector.entries){
            val settings = MongoClientSettings.builder()
                .uuidRepresentation(UuidRepresentation.STANDARD) // BSON 변화 표준 -> 기본을 따른다.
                .applyConnectionString(ConnectionString(uri)) // 접속 uri
                .serverApi(serverApi)
                .readPreference(ReadPreference.primary()) // 모든 읽기 작업이 primary 노드에서 수행이 된다. -> 일관된 읽기를 보장
                .build()
            try {
                val mongoClient = MongoClients.create(settings)
                mapper[c] = MongoTemplate(
                    SimpleMongoClientDatabaseFactory(mongoClient, c.dataBaseName)
                )
            } catch (e: Exception) {
                throw CustomException(ErrorCode.FailedToCreateMongoTemplate)
            }
        }

        return mapper
    }


}