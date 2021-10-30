package com.IOC.beans;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE})
//@Documented
public @interface MyBean {
    String value() default "TBean";
}
