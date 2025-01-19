package org.example.custom.requeest.annotation


@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class PageCustomAnnotation(
    val defaultValue: String = "DESC"
)