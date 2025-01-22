package org.example.config

import kotlinx.serialization.json.Json
import org.springframework.context.annotation.Configuration
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.http.codec.json.KotlinSerializationJsonDecoder
import org.springframework.http.codec.json.KotlinSerializationJsonEncoder
import org.springframework.http.converter.json.*
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
class WebFluxConfig(private val kJson: Json) : WebFluxConfigurer {

    override fun configureHttpMessageCodecs(configurer: ServerCodecConfigurer) {
        configurer.defaultCodecs().configureDefaultCodec { KotlinSerializationJsonHttpMessageConverter(kJson) }
        configurer.defaultCodecs().kotlinSerializationJsonEncoder(KotlinSerializationJsonEncoder(kJson))
        configurer.defaultCodecs().kotlinSerializationJsonDecoder(KotlinSerializationJsonDecoder(kJson))
    }

}