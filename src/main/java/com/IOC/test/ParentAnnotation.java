package com.IOC.test;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE})
@Documented
@Inherited  //©ирт╪лЁп
public @interface ParentAnnotation {

}
