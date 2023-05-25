package com.chen.nacos;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * demo配置
 *
 * @author chenzhiying
 * @date 2023-05-24
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(BeanPostProcessorsRegistrar.class)
public @interface EnableDemo {

    DemoProperties globalProperties();
}
