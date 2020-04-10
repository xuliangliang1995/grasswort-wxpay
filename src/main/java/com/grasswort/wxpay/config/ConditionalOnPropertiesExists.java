package com.grasswort.wxpay.config;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author xuliangliang
 * @Classname ConditionalOnPropertiesExists.java
 * @Description
 * @Date 2020/4/10
 * @blame Java Team
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(ConditionPropertiesNotEmpty.class)
public @interface ConditionalOnPropertiesExists {

    String[] value() default {};
}
