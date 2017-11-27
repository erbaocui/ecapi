package com.cn.controller;

import com.cn.annotation.Config;
import com.cn.annotation.JsonParam;
import com.cn.constant.CustomerType;
import com.cn.constant.Status;
import com.cn.controller.token.TokenManager;
import com.cn.controller.token.TokenModel;
import com.cn.model.AppLogin;
import com.cn.model.Customer;

import com.cn.param.InLogin;
import com.cn.param.OutCustomer;
import com.cn.service.IAppLoginService;
import com.cn.service.ICustomerService;


import com.cn.three.wx.WeichatService;
import com.cn.util.IdGenerator;
import com.cn.util.MD5Util;

import com.cn.vo.RetObj;
import org.apache.commons.beanutils.BeanUtils;
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
    private IAppLoginService appLoginService;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private WeichatService weichatService;



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
     *
     * @param
     *
     * @return
     */
    @RequestMapping(value="/login")
    @Config(methods = "login",module = "客户模块",needlogin = false,interfaceLog =true)
    public @ResponseBody
    RetObj login(@JsonParam InLogin inLogin,HttpServletRequest request) throws Exception{
        //在Session里保存信息
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        String type=inLogin.getType();
        if(type.equals(String.valueOf(CustomerType.MOBILE.getIndex()))) {
            Customer customer = new Customer();
            customer.setLoginName(inLogin.getLoginName());
            Customer c = customerService.getCustomerByEntity(customer);
            if (null != c) {
                if (c.getPassword().equals(inLogin.getPwd())) {
                    OutCustomer outCustomer=loginCommon(c);
                    retObj.setData(outCustomer);
                    retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
                } else {
                    retObj.setCode(Status.INVALID.getIndex());
                    retObj.setMsg(requestContext.getMessage("sys.customer.pwderror"));
                }
            } else {
                retObj.setCode(Status.INVALID.getIndex());
                retObj.setMsg(requestContext.getMessage("sys.customer.noperson"));
            }
        }
        if(type.equals(String.valueOf(CustomerType.WEICHAT.getIndex()))){
            if(weichatService.isAccessTokenIsInvalid(inLogin.getPwd(),inLogin.getLoginName())){
               Customer customer = new Customer();
               customer.setLoginName(inLogin.getLoginName());
               Customer c = customerService.getCustomerByEntity(customer);
               if(c==null){
                   customer.setId(IdGenerator.getId());
                   customer.setPassword(inLogin.getPwd());
                   customerService.addCustomer(customer);
                   c=customer;
               }
               OutCustomer outCustomer=loginCommon(c);
               retObj.setData(outCustomer);
               retObj.setMsg(requestContext.getMessage("sys.prompt.success"));

           }else{
               retObj.setCode(Status.INVALID.getIndex());
               retObj.setMsg(requestContext.getMessage("sys.login.wxerror"));
           }


        }

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
    private OutCustomer loginCommon(Customer c) throws Exception{
        AppLogin lastAppLogin = new AppLogin();
        lastAppLogin.setCustomerId(c.getId());
        lastAppLogin.setStatus(String.valueOf(Status.EFFECTIVE.getIndex()));
        lastAppLogin = appLoginService.getLastApploginByEntity(lastAppLogin);
        if (lastAppLogin != null) {
            TokenModel lastToken = tokenManager.getToken(lastAppLogin.getTokenId());
            if (lastToken != null) {
                tokenManager.deleteToken(lastToken.getToken());
            }
        }
        TokenModel token = tokenManager.createToken(c);
        AppLogin currentAppLogin = new AppLogin();

        currentAppLogin.setId(IdGenerator.getId());
        currentAppLogin.setCustomerId(c.getId());
        currentAppLogin.setCustomerName(c.getDisplayName());
        currentAppLogin.setLoginTime(new Date());
        currentAppLogin.setStatus(String.valueOf(Status.EFFECTIVE.getIndex()));
        currentAppLogin.setTokenId(token.getToken());
        appLoginService.addApplogin(currentAppLogin, lastAppLogin);
        OutCustomer outCustomer = new OutCustomer();
        BeanUtils.copyProperties(outCustomer, c);
        outCustomer.setName(c.getDisplayName());
        outCustomer.setToken(token.getToken());
        return   outCustomer;
    }


    public ICustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }

    public IAppLoginService getAppLoginService() {
        return appLoginService;
    }

    public void setAppLoginService(IAppLoginService appLoginService) {
        this.appLoginService = appLoginService;
    }

    public WeichatService getWeichatService() {
        return weichatService;
    }

    public void setWeichatService(WeichatService weichatService) {
        this.weichatService = weichatService;
    }
}

