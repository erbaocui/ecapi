package com.cn.three.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.cn.util.HttpUtil;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by home on 2017/11/25.
 */
@Service("weichatService ")
public class WeichatService {

    public static final String  USER_INFO_URL= "https://api.weixin.qq.com/sns/userinfo";
    public static final String  CHECK_URL=  "https://api.weixin.qq.com/sns/auth";

  public  WeichatUserInfo getUserInfo(String accessToken,String openId) {
            if(!isAccessTokenIsInvalid( accessToken,openId)){
                return null;
            }

            try {
                Map<String,Object> param=new HashMap<String,Object>();
                param.put("access_token",accessToken);
                param.put("openid",openId);
                String response=HttpUtil.doGet(USER_INFO_URL,param);
                JSONObject obj= JSON.parseObject( response);
                int errorCode =  obj.getIntValue("errcode");
                if (errorCode == 0) {
                    WeichatUserInfo userInfo = new  WeichatUserInfo();
                    userInfo.setOpenId(openId);
                    userInfo.setAccessToken(accessToken);
                    userInfo.setNickname(obj.getString("nickname"));
                    userInfo.setSex(obj.getString("sex"));
                    userInfo.setUserImg(obj.getString("headimgurl"));
                    return userInfo;
                }


            }  catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }


    public boolean isAccessTokenIsInvalid(String accessToken,String openId) {


        try {

            Map<String,Object> param=new HashMap<String,Object>();
            param.put("access_token",accessToken);
            param.put("openid",openId);
            String response=HttpUtil.doGet(CHECK_URL,param);
            JSONObject obj= JSON.parseObject( response);
            int errorCode =  obj.getIntValue("errcode");
            if (errorCode == 0) {
                    return true;
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static void main (String  args[]){
        String accessToken ="7D7BE073A3F3800ED4D1A2E6D5F0F0E4";
        String     openID="E69264E4564A5D73D3DF1989996864B8";
       /* String   openID ="7D7BE073A3F3800ED4D1A2E6D5F0F0E4";
        String   accessToken="E69264E4564A5D73D3DF1989996864B8";*/
        Integer expires_in =7200;
        new WeichatService().isAccessTokenIsInvalid(accessToken, openID);
        //WeichatService.getUserInfo(accessToken, openID);
    }

}
