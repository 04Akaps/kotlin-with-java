package org.example.config

import kotlinx.serialization.json.Json
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JsonConfig {

    @Bean
    fun kJson(): Json {
        return Json {
            prettyPrint = true
            ignoreUnknownKeys = true // 알 수 없는 키를 무시
            isLenient = true // JSON을 lenient하게 파싱
            encodeDefaults = true // 기본값을 포함하여 인코딩
        }
    }
}