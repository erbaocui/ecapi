package com.cn.three.qq;

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
@Service("qqService ")
public class QQService {
    public static final String  USER_INFO_URL= "https://graph.qq.com/user/get_user_info";
    public static final String  CHECK_URL=  "https://api.weixin.qq.com/sns/auth";

  public QQUserInfo getUserInfo(String accessToken,String openId) {
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
                    QQUserInfo userInfo = new QQUserInfo();
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
        String accessToken ="B3A5107738D24D868E000103CB4C4504";
        String     openID="DF395AE9D8361FAA2605DA5E51B8647B";
        Integer expires_in =7200;
        //WeichatUtil.isAccessTokenIsInvalid(accessToken, openID);
        //WeichatService.getUserInfo(accessToken, openID);
    }

}
