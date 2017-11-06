package com.cn.controller.token;

import com.cn.model.Customer;
import com.cn.model.User;

/**
 * Created by home on 2017/10/25.
 */

public  interface   TokenManager {

   //int tokenExpireSeconds = 7 * 24 * 3600;
   int tokenExpireSeconds =10*60;
    /**
     * 创建一个token关联上指定用户
     * @param userId 指定用户的id
     * @return 生成的token
     */
    public TokenModel createToken(Customer customer);

    /**
     * 检查token是否有效
     * @param model token
     * @return 是否有效
     */
    public boolean checkToken(String token);

    /**
     * 从字符串中解析token
     * @param authentication 加密后的字符串
     * @return
     */
    public  TokenModel getToken(String token);

    /**
     * 清除token
     * @param userId 登录用户的id
     */
    public void deleteToken(String token);

    public String createTokenId();
}
