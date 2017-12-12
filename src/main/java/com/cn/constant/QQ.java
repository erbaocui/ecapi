package com.cn.constant;

import com.cn.util.PropertiesUtil;

/**
 * Created by home on 2017/11/23.
 */
public class QQ {
    public static final String ANDRIOD_APP_ID= PropertiesUtil.getStringByKey("qq.android.appid", "three.properties");
    public static final String IOS_APP_ID= PropertiesUtil.getStringByKey("qq.ios.appid", "three.properties");
}
