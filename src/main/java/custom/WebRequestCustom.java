package custom;


import exception.CustomException;
import exception.ErrorCode;
import org.example.annotation.OrderCustomAnnotation;
import org.example.annotation.PageCustomAnnotation;
import org.example.annotation.SizeCustomAnnotation;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class WebRequestCustom implements HandlerMethodArgumentResolver {

    private static final List<String> ALLOWED_VALUES = Arrays.asList("ASC", "DESC", "asc", "desc");

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // Request에 대한 Annotaion 추가 되면, || 조건을 통해서 검증하는 로직을 추가 한다.
        return parameter.hasParameterAnnotation(OrderCustomAnnotation.class)
                || parameter.hasParameterAnnotation(PageCustomAnnotation.class)
                || parameter.hasParameterAnnotation(SizeCustomAnnotation.class);

    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory
    ) {

        if (parameter.hasParameterAnnotation(OrderCustomAnnotation.class)) {
            OrderCustomAnnotation customAnnotation = parameter.getParameterAnnotation(OrderCustomAnnotation.class);
            String order = webRequest.getParameter(Objects.requireNonNull(parameter.getParameterName()));

            if (order != null && !ALLOWED_VALUES.contains(order)) {
                throw new CustomException(ErrorCode.NotSupportedOrderRequest, order);
            }

            if (order == null) {
                order = customAnnotation.defaultSort();
            }

            return order.toUpperCase();
        }

        if (parameter.hasParameterAnnotation(PageCustomAnnotation.class)) {
            PageCustomAnnotation customAnnotation = parameter.getParameterAnnotation(PageCustomAnnotation.class);
            String page = webRequest.getParameter(parameter.getParameterName());

            if (page == null) {
                return customAnnotation.defaultPage();
            }

            return Integer.parseInt(page);
        }

        if (parameter.hasParameterAnnotation(SizeCustomAnnotation.class)) {
            SizeCustomAnnotation customAnnotation = parameter.getParameterAnnotation(SizeCustomAnnotation.class);
            String size = webRequest.getParameter(parameter.getParameterName());

            if (size == null) {
                return customAnnotation.defaultSize();
            }

            return Integer.parseInt(size);
        }

        return null;
    }

}
