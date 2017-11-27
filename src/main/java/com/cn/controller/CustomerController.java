package com.cn.controller;

import com.cn.annotation.Config;
import com.cn.annotation.JsonParam;
import com.cn.constant.CustomerType;
import com.cn.constant.Mob;
import com.cn.constant.Status;
import com.cn.controller.token.TokenManager;
import com.cn.controller.token.TokenModel;
import com.cn.model.Customer;
import com.cn.param.InChgPwd;
import com.cn.param.InCustomer;
import com.cn.param.InFindPwd;
import com.cn.param.InRegister;
import com.cn.service.ICustomerService;
import com.cn.three.sms.SmsVerifyKit;
import com.cn.util.IdGenerator;

import com.cn.vo.RetObj;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/customer")
@Scope("prototype")
public class CustomerController extends BaseController{

    Logger logger= Logger.getLogger(CustomerController.class.getName());

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


    /**
     * 客户注册
     * @param
     *
     * @return listMenu json
     */
    @ResponseBody
    @RequestMapping(value = "/register")
    @Config(methods = "register",module = "客户模块",needlogin = false,interfaceLog =true)
    public RetObj register(@JsonParam InRegister inRegister,HttpServletRequest request)throws Exception
    {
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        Customer customer=new Customer();
        customer.setLoginName(inRegister.getPhone());
        Customer c=customerService.getCustomerByEntity(customer);
        if(c!=null) {
            retObj.setCode(Status.INVALID.getIndex());
            retObj.setMsg(requestContext.getMessage("sys.customer.exist"));
            return retObj;
        }

        Integer status=new SmsVerifyKit(Mob.APP_KEY, inRegister.getPhone(),inRegister.getCc(), inRegister.getVc()).go();
        if(status.equals(200)){
            customer=new Customer();
            customer.setId(IdGenerator.getId());
            customer.setLoginName(inRegister.getPhone());
            customer.setDisplayName(inRegister.getName());
            customer.setPassword(inRegister.getPwd());
            customer.setType(String.valueOf(CustomerType.MOBILE.getIndex()));
            customer.setStatus("0");
            customerService.addCustomer(customer);

            retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
        }else{
            retObj.setCode(status);
            retObj.setMsg(requestContext.getMessage("sys.customer.vcerror"));
        }

        return retObj;

    }
    /**
     *  忘记密码
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/findpwd")
    @Config(methods = "findpwd",module = "客户模块",needlogin = false,interfaceLog =true)
    public @ResponseBody
    RetObj findpwd(@JsonParam InFindPwd inFindPwd,HttpServletRequest request)throws Exception
    {
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        Integer status=new SmsVerifyKit(Mob.APP_KEY, inFindPwd.getPhone(), inFindPwd.getCc(), inFindPwd.getVc()).go();
        if(status.equals(200)){
            Customer customer=new Customer();
            customer.setLoginName(inFindPwd.getPhone());
            Customer c=customerService.getCustomerByEntity(customer);
            if(c!=null){
                customer=new Customer();
                customer.setId(c.getId());
                customer.setPassword(inFindPwd.getPwd());
                customerService.modifyCustomer(customer);
                retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
            }else{
                retObj.setCode(Status.INVALID.getIndex());
                retObj.setMsg(requestContext.getMessage("sys.customer.noperson"));
            }

        }else{
            retObj.setCode(status);
            retObj.setMsg(requestContext.getMessage("sys.customer.vcerror"));
        }
        return retObj;

    }
    /**
     *  更改密码
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/chgpwd")
    @Config(methods = "chgpwd",module = "客户模块",needlogin = true,interfaceLog =true)
    public @ResponseBody
    RetObj chgpwd(@JsonParam InChgPwd inChgPwd,HttpServletRequest request)throws Exception
    {
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        String token=(String) request.getHeader("token");
        TokenModel tokenModel=tokenManager.getToken(token);
        Customer currentCustomer=tokenModel.getCustomer();
        if(currentCustomer.getPassword().equals(inChgPwd.getOldpwd())){
            Customer customer=new Customer();
            customer.setId(currentCustomer.getId());
            customer.setPassword(inChgPwd.getNewpwd());
            customerService.modifyCustomer(customer);
            tokenManager.deleteToken(token);
            retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
            }else{
                retObj.setCode(Status.INVALID.getIndex());
                retObj.setMsg(requestContext.getMessage("sys.customer.pwderror"));
            }
        return retObj;

    }






    /**
     * 客户信息更新
     * @param
     *
     * @return map json
     */
    @RequestMapping(value = "/update")
    @Config(methods = "update",module = "客户模块",needlogin = true,interfaceLog =true)
    public @ResponseBody
    RetObj update(@JsonParam InCustomer inCustomer,HttpServletRequest request)throws Exception {
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        String token=(String) request.getHeader("token");
        TokenModel tokenModel=tokenManager.getToken(token);
        Customer customer=tokenModel.getCustomer();
        customer.setDisplayName(inCustomer.getName());
        customer.setGender(inCustomer.getGender());
        customer.setBirthdate(inCustomer.getBirthdate());
        customer.setProvince(inCustomer.getProvince());
        customer.setCity(inCustomer.getCity());
        customer.setCounty(inCustomer.getCounty());
        customer.setHead(inCustomer.getHead());
        customerService.modifyCustomer(customer);
        tokenModel.setCustomer(customer);
        tokenManager.resetToken(tokenModel);
        retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
        return retObj;
    }




    public ICustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }

    public TokenManager getTokenManager() {
        return tokenManager;
    }

    public void setTokenManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }
}
