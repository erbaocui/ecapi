package com.cn.constant;

import com.cn.util.PropertiesUtil;

/**
 * Created by home on 2017/8/8.
 */
public class  SMS {
      public static final Boolean SWITCH= Boolean.valueOf(PropertiesUtil.getStringByKey("switch","sms.properties"));
      public static final String ACCESS_KEY_ID = PropertiesUtil.getStringByKey("accessKeyId","sms.properties");
      public static final String ACCESS_KEY_SECRET = PropertiesUtil.getStringByKey("accessKeySecret","sms.properties");
      public static final String  SIGN= PropertiesUtil.getStringByKey("sign", "sms.properties");
      public static final String TEMPLATE_ID_CODE= PropertiesUtil.getStringByKey("template.vcode","sms.properties");
      public static final String TEMPLATE_REMIND= PropertiesUtil.getStringByKey("template.remind","sms.properties");
     }
