package org.example.service

import exception.CustomException
import exception.ErrorCode
import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import lombok.RequiredArgsConstructor
import model.enums.CircuitCollector
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class CircuitService(
    private val circuitTemplate: Map<CircuitCollector, CircuitBreaker>,
    private  val httpClient: HttpClient
) {

    private  val testUrls = listOf(
        "http://localhost:7002/api/circuit/one",
        "http://localhost:7002/api/circuit/two",
        "http://localhost:7002/api/circuit/three",
    )

    // 가장 간단한 코루틴
    suspend fun testRoutine() : String = coroutineScope {

        val map = async {
            println("one start")
            delay(2000)
            println("one one one ")
        }

        val two = async {
            println("two start")
            delay(5000)
            println("twotwotwo")
        }

        map.await().toString() + two.await().toString()
    }

     fun testCircuitWithRoutine() : String  {

         // http calls
         val deferredResults: List<Deferred<String?>> = runBlocking {
             supervisorScope {
                 testUrls.map { url ->
                     async {
                         println("${url.split("/").last()} start")
                         fetchResult(url)
                     }
                 }
             }
         }

         // wait result
         val results = runBlocking {
             deferredResults.map { it.await() ?: "${it::class.simpleName?.toLowerCase()}_failed" }
         }

         val finalResult = results.joinToString("")
         println("Final Result: $finalResult")
         return finalResult
    }

    private suspend fun fetchResult(url: String): String? {
        return try {
            val result = httpClient.get(url).body<String>()
            println(result)
            result
        } catch (e: Exception) {
            println("Error in $url: ${e.message}")
            null
        }
    }

    private fun breaker(key: CircuitCollector): CircuitBreaker {
        return circuitTemplate.get(key) ?: throw CustomException(ErrorCode.FailedToFindBreaker)
    }


}