package org.example.controller


import exception.CustomException
import exception.ErrorCode
import org.example.service.CircuitService
import org.springframework.web.bind.annotation.*
import java.lang.Thread.sleep

@RestController
@RequestMapping("/api/circuit")
class CircuitController(private val circuitService: CircuitService) {

    @GetMapping("/one")
    fun one():String {
        sleep(2000)
        return "one"
    }

    @GetMapping("/two")
    fun two():String {
        sleep(1000)
        throw CustomException(ErrorCode.CircuitTest)
    }

    @GetMapping("/three")
    fun three():String {
        sleep(5000)
        return "three"
    }

    @GetMapping("/do-test")
    suspend fun test() {
        circuitService.testRoutine()
    }

    @GetMapping("/test-circuit-with-routine")
    fun testCircuitWithRoutine() {
        circuitService.testCircuitWithRoutine()
    }
}