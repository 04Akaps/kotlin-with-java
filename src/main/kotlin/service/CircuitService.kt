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
         val one: Deferred<String?>
         val two: Deferred<String?>
         val three: Deferred<String?>

         runBlocking {
             supervisorScope {
                 one = async {
                     println("one start")
                     try {
                         val result = httpClient.get("http://localhost:7002/api/circuit/one").body<String>()
                         println(result)
                         result
                     } catch (e: Exception) {
                         println("Error in one: ${e.message}")
                         null
                     }
                 }

                 two = async {
                     println("two start")
                     try {
                         val result = httpClient.get("http://localhost:7002/api/circuit/two").body<String>()
                         println(result)
                         result
                     } catch (e: Exception) {
                         println("Error in two: ${e.message}")
                         null
                     }
                 }

                 three = async {
                     println("three start")
                     try {
                         val result = httpClient.get("http://localhost:7002/api/circuit/three").body<String>()
                         println(result)
                         result
                     } catch (e: Exception) {
                         println("Error in three: ${e.message}")
                         null
                     }
                 }
             }

             // 모든 작업 결과 대기 및 결합
             val result = (one.await() ?: "one_failed") +
                     (two.await() ?: "two_failed") +
                     (three.await() ?: "three_failed")
             println("Final Result: $result")
         }

//        val one = async {
//           val res:String = httpClient.get("http://localhost:7002/api/circuit/one").body<String>()
//            println(res)
//        }
//
//        val two = async {
//            val res:String =  httpClient.get("http://localhost:7002/api/circuit/two").body<String>()
//            println(res)
//        }
//
//        val three = async {
//            val res:String = httpClient.get("http://localhost:7002/api/circuit/three").body<String>()
//            println(res)
//        }

        return ""
    }




    private fun breaker(key: CircuitCollector): CircuitBreaker {
        return circuitTemplate.get(key) ?: throw CustomException(ErrorCode.FailedToFindBreaker)
    }


//
//    fun callApi(api: CircuitCollector) {
//        val circuitBreaker = circuitTemplate[api]
//
//        // CircuitBreaker가 null이 아닌지 확인하고 사용
//        circuitBreaker?.let {
//            // CircuitBreaker를 통해 API 호출을 감쌈
//            val result = CircuitBreaker.decorateSupplier(it) {
//                // 실제 외부 API 호출 코드
//                externalApiCall(api)
//            }
//
//            try {
//                val response = result.get()  // API 응답 가져오기
//                println("API 응답: $response")
//            } catch (e: Throwable) {
//                // 회로가 열려 있거나 실패한 경우 예외 처리
//                println("API 호출 실패: ${e.message}")
//            }
//        } ?: run {
//            println("해당 API에 대한 CircuitBreaker 설정이 없습니다.")
//        }
//    }
//
//    // 실제 외부 API 호출을 처리하는 메서드 (예시)
//    private fun externalApiCall(api: CircuitCollector): String {
//        // 외부 API 호출 로직
//        return "API 응답: ${api.key}"  // 예시로 문자열 반환
//    }
}