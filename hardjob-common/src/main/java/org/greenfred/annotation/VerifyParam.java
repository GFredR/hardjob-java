package org.greenfred.annotation;

import org.greenfred.enums.VerifyRegexEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface VerifyParam {

    /**
     * 正则表校验
     * @return
     */
    VerifyRegexEnum regex() default VerifyRegexEnum.NO;

    int min() default -1;

    int max() default -1;

    boolean required() default false;
}
