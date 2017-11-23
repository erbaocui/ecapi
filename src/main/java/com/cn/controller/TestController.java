package com.cn.controller;

import com.cn.annotation.Config;
import com.cn.annotation.JsonParam;
import com.cn.param.OutCountryCode;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/test")
@Scope("prototype")
public class TestController extends BaseController{

    Logger logger= Logger.getLogger(TestController.class.getName());




    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }


    /**
     * 登录
     * @param
     * @param
     *
     *
     * @return
     */
    @RequestMapping(value="/test")
    @Config(methods = "login",module = "客户模块",needlogin = false,interfaceLog =true)
    public @ResponseBody
    void login(HttpServletRequest request,@JsonParam OutCountryCode cc) throws Exception{
        //在Session里保存信息

        //CountryCode cc=( CountryCode) JSONObject.toJavaObject(JSON.parseObject(q), CountryCode.class);
        System.out.println(cc);

    }

    public  static void main(String args[]){
        long date=new Date().getTime();
        System.out.println(date);
    }


}
