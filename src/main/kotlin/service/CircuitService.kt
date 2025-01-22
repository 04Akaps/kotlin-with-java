package org.example.service

import exception.CustomException
import exception.ErrorCode
import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.ktor.client.*
import lombok.RequiredArgsConstructor
import model.enums.CircuitCollector
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.util.function.Tuple3
import reactor.util.function.Tuples
import kotlinx.coroutines.coroutineScope

@Service
@RequiredArgsConstructor
class CircuitService(
    private val circuitTemplate: Map<CircuitCollector, CircuitBreaker>,
    private  val httpClient: HttpClient
) {

    suspend fun testRoutine() : String = coroutineScope {
        ""
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