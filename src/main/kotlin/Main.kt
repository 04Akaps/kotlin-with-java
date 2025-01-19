package org.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication
@EnableAspectJAutoProxy
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}