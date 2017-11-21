package com.cn.constant;

import com.cn.util.PropertiesUtil;

/**
 * Created by home on 2017/8/11.
 */
public class Qiniu {
    public static final String ACCESS_KEY= PropertiesUtil.getStringByKey("qiniu.AccessKey", "qiniu.properties");
    public static final String SECRET_KEY= PropertiesUtil.getStringByKey("qiniu.SecretKey", "qiniu.properties");
    public static final String BUCKET_NAME= PropertiesUtil.getStringByKey("qiniu.bucketName", "qiniu.properties");
    public static final String DOMAIN_NAME= PropertiesUtil.getStringByKey("qiniu.domainName", "qiniu.properties");
}
