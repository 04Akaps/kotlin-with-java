package model.enums;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

@AllArgsConstructor
@Getter
public enum CircuitCollector {
    API_1("API:1", 50.0f, Duration.ofSeconds(5), 10),
    API_2("API:2", 70.0f, Duration.ofSeconds(3), 15),
    API_3("API:3", 40.0f, Duration.ofSeconds(10), 20);


    private final String key;
    private final float failureRateThreshold;
    private final Duration waitDurationInOpenState;
    private final int slidingWindowSize;

    public CircuitBreakerConfig toConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(failureRateThreshold) // 실패율 임계값 -> 50%인 경우 windowSize 기준으로 실패율을 계산하여 차단한다.
                .waitDurationInOpenState(waitDurationInOpenState) // open 이 유지되는 시간
                .slidingWindowSize(slidingWindowSize) // 계산 임계값
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED) // 횟수 기반으로 카운트를 집계
                .build();
    }
}
