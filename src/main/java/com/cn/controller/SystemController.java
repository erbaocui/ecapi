package com.cn.controller;


import com.cn.annotation.Config;
import com.cn.model.Version;
import com.cn.param.OutCountryCode;
import com.cn.param.OutQiNiu;
import com.cn.param.OutVersion;
import com.cn.service.IVersionService;
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
    @Autowired
    private IVersionService versionService;



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
    /*版本检查
     * @param
     *
     * @return listMenu json
    */

    @RequestMapping(value = "/version")
    public @ResponseBody
    @Config(methods = "version" ,module = "系统",needlogin = false,interfaceLog =true)
    RetObj  version(HttpServletRequest request)throws Exception
    {
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        String type=request.getHeader("type");
        String appVersion =request.getHeader("v");
        Version version=new Version();
        version.setType(type);
        version.setStatus("0");
        Version v=versionService.getVersionByEntity(version);
        String serverVersion=v.getV();
        OutVersion outVersion=new OutVersion();
        if(compareVersion(serverVersion, appVersion)>0){
            outVersion.setHasnew("1");
            outVersion.setVersion(v.getV());
            outVersion.setUrl(v.getUrl());
            outVersion.setDesc(v.getRemark());
        }else{
            outVersion.setHasnew("0");

        }
        retObj.setData(outVersion);
        retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
        return retObj;
    }

    public QiniuService getQiniuService() {
        return qiniuService;
    }

    public void setQiniuService(QiniuService qiniuService) {
        this.qiniuService = qiniuService;
    }

    public IVersionService getVersionService() {
        return versionService;
    }

    public void setVersionService(IVersionService versionService) {
        this.versionService = versionService;
    }

    /**
            * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
    * @param version1
    * @param version2
    * @return
            */
    public static int compareVersion(String version1, String version2) throws Exception {
        if (version1 ==null || version2 ==null) {
            throw new Exception("compareVersion error:illegal params.");
        }
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

}
