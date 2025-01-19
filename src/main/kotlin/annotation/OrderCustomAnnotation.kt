package org.example.annotation

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class OrderCustomAnnotation(val defaultSort: String = "DESC")
