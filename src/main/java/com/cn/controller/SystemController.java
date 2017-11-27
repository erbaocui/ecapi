package com.cn.controller;


import com.cn.annotation.Config;
import com.cn.param.OutCountryCode;
import com.cn.param.OutQiNiu;
import com.cn.three.qiniu.QiniuService;
import com.cn.util.PropertiesUtil;
import com.cn.vo.RetObj;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/sys")
@Scope("prototype")
public class SystemController extends BaseController{

    Logger logger= Logger.getLogger(SystemController.class.getName());
    @Autowired
    private QiniuService qiniuService;



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
        List<OutCountryCode> list=new ArrayList<OutCountryCode>();
        for(String cc:ccs){
            OutCountryCode countryCode=new OutCountryCode();
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
        OutQiNiu qiNiu=new OutQiNiu();
        qiNiu.setToken(qiniuService.getUpToken());
        qiNiu.setDomain(PropertiesUtil.getStringByKey("qiniu.domainName", "qiniu.properties"));
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        retObj.setData(qiNiu);
        retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
        return retObj;
    }

    public QiniuService getQiniuService() {
        return qiniuService;
    }

    public void setQiniuService(QiniuService qiniuService) {
        this.qiniuService = qiniuService;
    }
}
