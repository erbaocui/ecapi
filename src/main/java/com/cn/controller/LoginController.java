package com.cn.controller;

import com.cn.anno.Config;
import com.cn.constant.Status;
import com.cn.controller.token.TokenManager;
import com.cn.controller.token.TokenModel;
import com.cn.model.AppLogin;
import com.cn.model.Customer;

import com.cn.service.ICustomerService;


import com.cn.util.MD5Util;

import com.cn.vo.RetObj;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
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
     *          loginName
     * @param password
     *          密码
     * @return
     */
    @RequestMapping(value="/login")
    @Config(methods = "login",module = "客户模块",needlogin = false,interfaceLog =true)
    public @ResponseBody
    RetObj login(HttpServletRequest request,String loginName , String password) throws Exception{
        //在Session里保存信息
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        Customer  customer=new Customer();
        Map map=new HashMap();
        customer.setLoginName(loginName);
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
            map.put("customer",c);
            map.put("token",token.getToken());
            retObj.setData(map);


        }else{
            retObj.setCode(Status.INVALID.getIndex());
            retObj.setMsg(requestContext.getMessage("sys.prompt.fail"));
        }

        retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
        return retObj;


    }


    /**
     * 退出系统
     * @param
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/logout")
    @Config(methods = "logout",module = "登陆模块",needlogin = true,interfaceLog =true)
    public @ResponseBody RetObj logout(HttpServletRequest request) throws Exception{

        String token=(String) request.getHeader("token");
        TokenModel tokenModel=null;
        if(token!=null&&(!token.equals(""))){
            tokenManager.deleteToken(token);

        }
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
        return retObj;


    }


    public ICustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }
}
