package com.IOC.test;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE})
@Documented
@Inherited  //���Լ̳�
public @interface ParentAnnotation {

}
