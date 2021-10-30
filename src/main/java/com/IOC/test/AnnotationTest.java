package com.IOC.test;

import com.IOC.beans.UserController;

import java.lang.annotation.Annotation;

public class AnnotationTest {

    public static void main(String[] args) {
        Annotation[] allAnnos = BeanClass.class.getAnnotations();
        Annotation[] deAnnos = BeanClass.class.getDeclaredAnnotations();
        Annotation subAnnotation = BeanClass.class.getAnnotation(BeanAnnotation.class);
        Annotation parentAnnotation = BeanClass.class.getAnnotation(ParentAnnotation.class);
        printAnnotation("all",allAnnos);
        printAnnotation("declare",deAnnos);
        printAnnotation("sub",subAnnotation);
        printAnnotation("parent",parentAnnotation);
        Annotation[] annotations = UserController.class.getAnnotations();
        System.out.println(annotations[0]);
        System.out.println(annotations[1]);
    }

    private static void printAnnotation(String msg,Annotation... annotations){
        System.out.println("=============="+msg+"======================");
        if(annotations == null){
            System.out.println("Annotation is null");
        }
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
        System.out.println();
    }
}
