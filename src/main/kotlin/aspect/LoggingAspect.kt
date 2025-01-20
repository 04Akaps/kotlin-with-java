package org.example.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class LoggingAspect {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Pointcut("execution(* org.example..*(..))")
    fun all() {}

    @Pointcut("execution(* org.example..*Controller.*(..))")
    fun controller() {}

    @Pointcut("execution(* org.example..*Service.*(..))")
    fun service() {}

    @Around("all()")
    fun logging(joinPoint: ProceedingJoinPoint): Any? {
        val start = System.currentTimeMillis()
        return try {
            joinPoint.proceed()
        } catch (e: Throwable) {
            throw RuntimeException(e)
        } finally {
            val finish = System.currentTimeMillis()
            val timeMs = finish - start
            log.info("log = {}", joinPoint.signature)
            log.info("timeMs = {}", timeMs)
        }
    }

    @Before("controller() || service()")
    fun beforeLogic(joinPoint: JoinPoint) {
        val methodSignature = joinPoint.signature as MethodSignature
        val method = methodSignature.method
        log.info("method = {}", method.name)

    }

    @After("controller() || service()")
    fun afterLogic(joinPoint: JoinPoint) {
        val methodSignature = joinPoint.signature as MethodSignature
        val method = methodSignature.method
        log.info("method = {}", method.name)

    }
}