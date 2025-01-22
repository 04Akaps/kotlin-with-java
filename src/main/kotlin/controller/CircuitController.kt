package org.example.controller


import exception.CustomException
import exception.ErrorCode
import model.entity.Movies
import model.entity.MoviesWithComments
import model.response.Response
import org.example.annotation.OrderCustomAnnotation
import org.example.annotation.PageCustomAnnotation
import org.example.annotation.SizeCustomAnnotation
import org.example.service.CircuitService
import org.example.service.MongoService
import org.jetbrains.annotations.NotNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/circuit")
class CircuitController(private val circuitService: CircuitService) {

    @GetMapping("/one")
    fun one():String {
        return "one"
    }

    @GetMapping("/two")
    fun two():String {
        throw CustomException(ErrorCode.CircuitTest)
    }

    @GetMapping("/three")
    fun three():String {
        return "three"
    }

    @GetMapping("/do-test")
    fun test() {
        circuitService.callApiUsingCircuit()
    }
}