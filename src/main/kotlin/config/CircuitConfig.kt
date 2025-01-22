package org.example.config

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import model.enums.CircuitCollector
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class CircuitConfig {

    @Bean
    fun circuitBreakerRegistry(): CircuitBreakerRegistry {
        return CircuitBreakerRegistry.ofDefaults()
    }

    @Bean
    fun circuitTemplate(circuitBreakerRegistry: CircuitBreakerRegistry): Map<CircuitCollector, CircuitBreaker> {

        val circuitBreakerMap = EnumMap<CircuitCollector, CircuitBreaker>(CircuitCollector::class.java)

        for (c in CircuitCollector.entries) {
            val circuitBreaker = circuitBreakerRegistry.circuitBreaker(c.key, c.toConfig())
            circuitBreakerMap[c] = circuitBreaker
        }

        return circuitBreakerMap
    }
}