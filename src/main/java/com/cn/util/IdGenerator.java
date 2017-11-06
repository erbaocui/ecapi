package com.cn.util;

import java.util.UUID;

/**
 * Created by home on 2017/11/3.
 */
public class IdGenerator {

    public static String getId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
