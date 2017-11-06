package com.cn.controller;

import com.cn.anno.Config;
import com.cn.constant.Status;
import com.cn.controller.token.TokenManager;
import com.cn.controller.token.TokenModel;
import com.cn.model.AppLogin;
import com.cn.model.Customer;

import com.cn.service.ICustomerService;


import com.cn.util.MD5Util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/login")
@Scope("prototype")
public class LoginController extends BaseController{

    Logger logger= Logger.getLogger(LoginController.class.getName());


    @Autowired
    private ICustomerService customerService;
    @Autowired
    private TokenManager tokenManager;



    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public TokenManager getTokenManager() {
        return tokenManager;
    }

    public void setTokenManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    /**
     * 登录
     * @param
     * @param
     *           customerName
     * @param password
     *          密码
     * @return
     */
    @RequestMapping(value="/login")
    @Config(methods = "测试方法",module = "测试模块",needlogin = false,interfaceLog =true)
    public @ResponseBody Map login(String customerName,String password) throws Exception{
        //在Session里保存信息
        Customer  customer=new Customer();
        Map map=new HashMap();
        customer.setLoginName(customerName);
        Customer c=customerService.getCustomerByEntity( customer);
        if(null!=c&&c.getPassword().equals(MD5Util.md5(password))){
            AppLogin lastAppLogin=new  AppLogin();
            lastAppLogin.setCustomerId(c.getId());
            lastAppLogin.setStatus(String.valueOf(Status.EFFECTIVE.getIndex()));
            lastAppLogin=customerService.getLastApploginByEntity(lastAppLogin);
            if( lastAppLogin!=null){
                TokenModel  lastToken= tokenManager.getToken(lastAppLogin.getTokenId());
                if(lastToken!=null) {
                    tokenManager.deleteToken(lastToken.getToken());
                }
            }
            TokenModel token=tokenManager.createToken(c);
            AppLogin currentAppLogin=new  AppLogin();
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            currentAppLogin.setId(uuid);
            currentAppLogin.setCustomerId(c.getId());
            currentAppLogin.setCustomerName(c.getDisplayName());
            currentAppLogin.setLoginTime(new Date());
            currentAppLogin.setStatus(String.valueOf(Status.EFFECTIVE.getIndex()));
            currentAppLogin.setTokenId(token.getToken());
            lastAppLogin.setStatus(String.valueOf(Status.INVALID.getIndex()));
            customerService.addApploginCustomer(currentAppLogin, lastAppLogin);
            map.put("token",token.getToken());


        }else{
            map.put("test","");
        }


        return map;

    }


    /**
     * 退出系统
     * @param
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/logout")
    public @ResponseBody Map logout() throws Exception{
        //清除Session
        //User user=new User();
        Map map=new HashMap();
        /*user.setId("745e706be84140c8a93c181497ef3c7d");
  String token= tokenManager.getToken(user);
        if(token!=null){
                tokenManager.deleteToken(user.getId());
        }*/
        return map;

    }


    public ICustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }
}
