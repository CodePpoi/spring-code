package com.AOP2.annoation;

import java.lang.annotation.*;

/**
 * @Description
 * @Author 蚂蚁不是ant
 * @Date 2020/7/31 23:24
 * @Version V1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAfter {
    String value() default "";
}
