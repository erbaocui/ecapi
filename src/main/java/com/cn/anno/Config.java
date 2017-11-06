package com.cn.anno;

import java.lang.annotation.*;

/**
 * Created by home on 2017/11/1.
 */
@Documented
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)

@Inherited
public @interface Config {
        String module()  default "";
        String methods()  default "";
        boolean needlogin()  default true;
        boolean interfaceLog()  default false;

}

