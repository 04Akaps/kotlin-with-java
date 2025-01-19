package org.example.custom.requeest

import jakarta.servlet.http.HttpServletRequest
import org.example.custom.requeest.annotation.PageCustomAnnotation
import org.springframework.stereotype.Component
import org.springframework.core.MethodParameter
import org.springframework.lang.Nullable
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class PageCustom(private val request: HttpServletRequest) : HandlerMethodArgumentResolver {
    companion object {
        val ALLOWED_VALUES = listOf("ASC", "DESC", "asc", "desc")
    }

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(PageCustomAnnotation::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        @Nullable mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        @Nullable binderFactory: WebDataBinderFactory?
    ): Any? {
        val customAnnotation = parameter.getParameterAnnotation(PageCustomAnnotation::class.java)
        var order = request.getParameter(parameter.parameterName)

        if (order == null || !ALLOWED_VALUES.contains(order)) {
            order = customAnnotation?.defaultValue ?: "DESC"
        }

        return order.uppercase() // Ensure the value is always in uppercase
    }
}