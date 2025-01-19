package org.example.annotation

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class SizeCustomAnnotation(val defaultSize: Int = 50)