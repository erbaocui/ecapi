package com.cn.annotation;

import java.lang.annotation.*;

/**
 * Created by home on 2017/11/1.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonParam {
}