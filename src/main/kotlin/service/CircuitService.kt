package org.example.service

import exception.CustomException
import exception.ErrorCode
import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.ktor.client.*
import lombok.RequiredArgsConstructor
import model.enums.CircuitCollector
import org.springframework.stereotype.Service
import kotlinx.coroutines.coroutineScope

@Service
@RequiredArgsConstructor
class CircuitService(
    private val circuitTemplate: Map<CircuitCollector, CircuitBreaker>,
    private  val httpClient: HttpClient
) {

    suspend fun testRoutine() : String = coroutineScope {
//        val map = async {
//            kakaoMapAdapter.getMapData(partnerKey)
//        }
//
//
//        val payment = async {
//            offlinePaymentAdapter.getPartner(partnerKey)
//        }
//
//        val benefits = async {
//            benefitAdapter.getBenefits(partnerKey)
//        }
//
//        // 2단계
//        // map의 API 응답값을 사용하므로, map.await()으로 응답을 받고 나서 async가 실행됨
//        val bizPartner = async {
//            bizPartnerAdapter.getPartnerHub(map.await().partnerCode)
//        }
//
//        // 3단계 - paymentInfo, bizPartner API 2개에 종속적
//        // 2개 API가 모두 응답을 받고 나면 async 내부의 로직이 실행됨
//        val membership = async {
//            membershipAdapter.getUserMembership(
//                userAccountId,
//                companyId = payment.await().companyId,
//                partnerId = bizPartner.await().getMembershipPartnerId(),
//            )
//        }
//
//        PartnerHub.Response(
//            placeName = map.await().placeName,
//            bizHours = map.await().bizHour?.toDisplay(),
//            paymentMethods = payment.await().paymentMethod.toList(),
//            keywords = bizPartner.await().keywords,
//            benefits = BizEventThenBenefits.of(bizPartner.await(), benefits.await()).toList(),
//            notice = bizPartner.await().notice,
//            membership = MembershipArea.of(membership.await()),
//            buttonArea = ButtonArea.of(payment.await(), membership.await())
//        )


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