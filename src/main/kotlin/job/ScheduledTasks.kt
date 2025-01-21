package org.example.job

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component


@Component
class ScheduledTasks {
    @Scheduled(fixedRate = 5000)  // 5초마다
    fun performTask() {
        println("주기적으로 실행되는 작업")
    }

    @Scheduled(cron = "0 0 * * * *") // 매 정시에 실행 (크론 표현식)
    fun performTaskUsingCron() {
        println("크론 표현식을 사용하여 주기적으로 실행되는 작업")
    }
}