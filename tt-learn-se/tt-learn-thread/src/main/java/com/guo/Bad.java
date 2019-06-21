package com.guo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.LOCAL_VARIABLE,ElementType.ANNOTATION_TYPE,ElementType.METHOD,ElementType.PACKAGE})
public @interface Bad {

     Class linkToGood() default Object.class;
}
