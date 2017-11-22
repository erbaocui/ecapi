package com.cn.controller;


import com.cn.anno.Config;
import com.cn.appvo.CountryCode;
import com.cn.appvo.QiNiu;
import com.cn.constant.Status;
import com.cn.model.Customer;
import com.cn.service.ICustomerService;
import com.cn.util.MD5Util;
import com.cn.util.PropertiesUtil;
import com.cn.util.StringUtil;
import com.cn.view.ViewExcel;
import com.cn.vo.RetObj;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/system")
@Scope("prototype")
public class SystemController extends BaseController{

    Logger logger= Logger.getLogger(SystemController.class.getName());



    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /*手机国家代码列表查询
     * @param
     *
     * @return listMenu json
    */
    @RequestMapping(value = "/cc")
    public @ResponseBody
    @Config(methods = "countryCode",module = "系统",needlogin = false,interfaceLog =true)
    RetObj countryCode(HttpServletRequest request,HttpSession session)throws Exception
    {
        RequestContext requestContext=new RequestContext(request);
        String str=requestContext.getMessage("cc.countrycode");
        String[] ccs=str.split(",");
        List<CountryCode> list=new ArrayList<CountryCode>();
        for(String cc:ccs){
            CountryCode countryCode=new CountryCode();
            String[] items=cc.split(":");
            countryCode.setCountry(items[0]);
            countryCode.setCode(items[1]);
            list.add(countryCode);

        }

        RetObj retObj=new RetObj();
        retObj.setData(list);
        return retObj;
    }
    /*手机国家代码列表查询
     * @param
     *
     * @return listMenu json
    */
    @RequestMapping(value = "/qiniu")
    public @ResponseBody
    @Config(methods = "qiniu" ,module = "系统",needlogin = false,interfaceLog =true)
    RetObj qiniu(HttpServletRequest request,HttpSession session)throws Exception
    {
        QiNiu qiNiu=new QiNiu();
        qiNiu.setAccessKey(PropertiesUtil.getStringByKey("qiniu.AccessKey", "qiniu.properties"));
        qiNiu.setSecretKey(PropertiesUtil.getStringByKey("qiniu.SecretKey", "qiniu.properties"));
        qiNiu.setBucket(PropertiesUtil.getStringByKey("qiniu.bucketName", "qiniu.properties"));
        qiNiu.setDomain(PropertiesUtil.getStringByKey("qiniu.domainName", "qiniu.properties"));
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        retObj.setData(qiNiu);
        retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
        return retObj;
    }





}
