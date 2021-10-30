package com.IOC.beans;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD})
//@Documented
public @interface AutoInject {

    String value() default "";
}
