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

  public QQUserInfo getUserInfo(String accessToken,String openId,String appid) {

            try {
                Map<String,Object> param=new HashMap<String,Object>();
                param.put("access_token",accessToken);
                param.put("openid",openId);
                param.put("oauth_consumer_key",appid);
                String response=HttpUtil.doGet(USER_INFO_URL,param);
                JSONObject obj= JSON.parseObject( response);
                int ret =  obj.getIntValue("ret");
                if (ret == 0) {
                    QQUserInfo userInfo = new QQUserInfo();
                    userInfo.setOpenId(openId);
                    userInfo.setAccessToken(accessToken);
                    userInfo.setNickname(obj.getString("nickname"));
                    userInfo.setProvince(obj.getString("province"));
                    userInfo.setCity(obj.getString("city"));
                    userInfo.setGender(obj.getString("gender"));
                    userInfo.setUserImg(obj.getString("figureurl_qq_1"));
                    return userInfo;
                }


            }  catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }




  /*  public static void main (String  args[]){
        String accessToken ="9BA4E5F2E55AB80184DCF73F8B1ED552";
        String     openID="4A37C24A9446E450082A3861A7669033";
        Integer expires_in =7200;
        //new QQService().getUserInfo(accessToken, openID);
        //WeichatService.getUserInfo(accessToken, openID);
    }*/

}
