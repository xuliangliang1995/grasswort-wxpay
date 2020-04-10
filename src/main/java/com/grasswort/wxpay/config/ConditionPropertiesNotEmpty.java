package com.grasswort.wxpay.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Arrays;
import java.util.Map;

/**
 * @author xuliangliang
 * @Classname ConditionPropertiesNotEmpty.java
 * @Description
 * @Date 2020/4/10
 * @blame Java Team
 */
public class ConditionPropertiesNotEmpty implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Map<String, Object> annotationAttributes = annotatedTypeMetadata.getAnnotationAttributes(ConditionalOnPropertiesExists.class.getName());
        String[] properties = (String[]) annotationAttributes.get("value");
        return Arrays.stream(properties)
                .filter(property -> StringUtils.isBlank(conditionContext.getEnvironment().getProperty(property)))
                .count() == 0;
    }
}
