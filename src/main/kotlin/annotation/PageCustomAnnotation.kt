package org.example.annotation

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class PageCustomAnnotation(val defaultPage: Int = 1)