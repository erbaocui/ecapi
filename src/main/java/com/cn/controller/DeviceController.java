package com.cn.controller;

import com.cn.annotation.Config;
import com.cn.annotation.JsonParam;


import com.cn.constant.PicType;
import com.cn.constant.Status;

import com.cn.model.*;

import com.cn.param.*;
import com.cn.service.*;
import com.cn.three.sms.SmsVerifyKit;
import com.cn.util.CoodinateCovertor;
import com.cn.util.DateUtil;
import com.cn.util.IdGenerator;
import com.cn.util.LngLat;
import com.cn.vo.RetObj;
import com.github.pagehelper.PageHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/device")
@Scope("prototype")
public class DeviceController extends BaseController{

    Logger logger= Logger.getLogger(DeviceController.class.getName());

    @Autowired
    private IDeviceService deviceService;
    @Autowired
    private ICustomerDeviceService customerDeviceService;
    @Autowired
    private IRecordService recordService;
    @Autowired
    private IParamService paramService;

    @Autowired
    private IStatisticsService statisticsService;



    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }


    /**
     * 设备绑定
     * @param
     *
     * @return  RetObj json
     */
    @ResponseBody
    @RequestMapping(value = "/bind")
    @Config(methods = "bind",module = "设备模块",needlogin =true,interfaceLog =true)
    public RetObj bing(@JsonParam InDeviceBind inDeviceBind,HttpServletRequest request)throws Exception
    {
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        Device device=new Device();
        device.setId(inDeviceBind.getId());
        device.setName(inDeviceBind.getName());
        CustomerDevice customerDevice=new CustomerDevice();
        customerDevice.setDevice(device);
        customerDevice.setCustomer(getCurrentCustomer(request));
        customerDeviceService.bindCustomerDevice(customerDevice);
        retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
        return retObj;

    }

    /**
     * 设备解绑
     * @param
     *
     * @return  RetObj json
     */
    @ResponseBody
    @RequestMapping(value = "/unbind")
    @Config(methods = "unbind",module = "设备模块",needlogin =true,interfaceLog =true)
    public RetObj unbing(@JsonParam InDeviceUnbind inDeviceUnbind,HttpServletRequest request)throws Exception
    {
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        Device device=new Device();
        device.setId(inDeviceUnbind.getId());
        CustomerDevice customerDevice=new CustomerDevice();
        customerDevice.setDevice(device);
        customerDevice.setCustomer(getCurrentCustomer(request));
        customerDeviceService.unbindCustomerDevice(customerDevice);
        retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
        return retObj;

    }

    /**
     * 设备断开链接
     * @param
     *
     * @return  RetObj json
     */
    @ResponseBody
    @RequestMapping(value = "/disconnect")
    @Config(methods = "disconnect",module = "设备模块",needlogin =true,interfaceLog =true)
    public RetObj disconnect(@JsonParam InDeviceDisconncet inDeviceDisconncet,HttpServletRequest request)throws Exception
    {
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        Device device=new Device();
        device.setId(inDeviceDisconncet.getId());
        device.setLat(inDeviceDisconncet.getLat());
        device.setLon(inDeviceDisconncet.getLon());
        deviceService.modifyDevice(device);
        retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
        return retObj;

    }

    /**
     * 设备列表
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/list")
    @Config(methods = "list",module = "设备模块",needlogin = true,interfaceLog =true)
    public @ResponseBody
    RetObj list(@JsonParam InDevice inDevice,HttpServletRequest request)throws Exception
    {
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        CustomerDevice customerDevice=new CustomerDevice();
        customerDevice.setCustomer(getCurrentCustomer(request));
        customerDevice.setStatus("0");
        List<CustomerDevice> list=customerDeviceService.getCustomerDevicePageByEntity(customerDevice);
        List<OutBindDevice>  outList=new ArrayList<OutBindDevice>();
        for(CustomerDevice cd:list){
            OutBindDevice   outBindDevice=new OutBindDevice();
            outBindDevice.setId(cd.getDevice().getId());
            outBindDevice.setName(cd.getDevice().getName());
            outList.add(outBindDevice);
        }
        retObj.setData(outList);
        retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
        return retObj;
    }

    /**
     * 设备信息
     * @param
     *
     * @return  RetObj json
     */
    @ResponseBody
    @RequestMapping(value = "/info")
    @Config(methods = "info",module = "设备模块",needlogin =true,interfaceLog =true)
    public RetObj info(@JsonParam InDevice inDevice,HttpServletRequest request)throws Exception {
        RetObj retObj = new RetObj();
        RequestContext requestContext = new RequestContext(request);
        Device device = new Device();
        device.setId(inDevice.getId());
        CustomerDevice customerDevice = new CustomerDevice();
        customerDevice.setDevice(device);
        customerDevice.setCustomer(getCurrentCustomer(request));

        CustomerDevice cd = customerDeviceService.getCustomerDeviceByEntity(customerDevice);
        if (cd != null) {
            OutDevice outDevice = new OutDevice();
            outDevice.setName(cd.getDevice().getName());
            outDevice.setId(cd.getDevice().getId());
            if (cd.getDevice().getLon() != null){
                outDevice.setLon(cd.getDevice().getLon().doubleValue());
             }
            if (cd.getDevice().getLat() != null){
               outDevice.setLat(cd.getDevice().getLat().doubleValue());
            }
            if(cd.getDevice().getLon() != null&&cd.getDevice().getLat() != null){
                LngLat lngLat=new LngLat();
                lngLat.setLantitude(cd.getDevice().getLat().doubleValue());
                lngLat.setLongitude(cd.getDevice().getLon().doubleValue());
                lngLat= CoodinateCovertor.bd_encrypt(lngLat);
                outDevice.setBaiduLat(lngLat.getLantitude());
                outDevice.setBaiduLon(lngLat.getLongitude());
            }
            if (cd.getUseCount()!= null){
                outDevice.setUseCount(cd.getUseCount());
            }
            if (cd.getChargeCount()!= null){
                outDevice.setChargeCount(cd.getChargeCount());
            }
            Param param=null;
            //一氧化氮
            param=new Param();
            param.setKv("co");
            param=paramService.getParamByEntity(param);
            int coRate=1;
            if(param.getValue()!=null){
                coRate=Integer.valueOf(param.getValue());
            }
            outDevice.setCo(outDevice.getUseCount()*coRate);
            //焦油
            param=new Param();
            param.setKv("tar");
            param=paramService.getParamByEntity(param);
            int tarRate=1;
            if(param.getValue()!=null){
                    tarRate=Integer.valueOf(param.getValue());
            }
            outDevice.setTar(outDevice.getUseCount()*tarRate);
            //健康
            param=new Param();
            param.setKv("health");
            param=paramService.getParamByEntity(param);
            int health=1;
            if(param.getValue()!=null){
                health=Integer.valueOf(param.getValue());
            }
            outDevice.setHealth(outDevice.getUseCount()*health);
            //尼古丁
            param=new Param();
            param.setKv("nicotine");
            param=paramService.getParamByEntity(param);
            int nicotine=1;
            if(param.getValue()!=null){
                nicotine=Integer.valueOf(param.getValue());
            }
            outDevice.setNicotine(outDevice.getUseCount()*nicotine);
            //app 分享Icon
            param=new Param();
            param.setKv("app_share_icon");
            param=paramService.getParamByEntity(param);
            outDevice.setAppShareIcon(param.getValue());

            //总使用次数
            Customer customer=getCurrentCustomer( request);
            Integer totalCount=statisticsService.getAll(customer.getId());
            //app分享title
            param=new Param();
            param.setKv("use_time");
            param=paramService.getParamByEntity(param);
            int useTime=Integer.valueOf(param.getValue());
            useTime=useTime*totalCount/60;
            param=new Param();
            param.setKv("app_share_title");
            param=paramService.getParamByEntity(param);
            String appShareTitle=param.getValue();
            appShareTitle=appShareTitle.replace("${useTime}",String.valueOf(useTime));
            outDevice.setAppShareTitle(appShareTitle);
            //app 分享content
            param=new Param();
            param.setKv("app_share_content");
            param=paramService.getParamByEntity(param);
            String  appShareContent=param.getValue();
            appShareContent=appShareContent.replace("${tar}",String.valueOf(tarRate*totalCount));
            outDevice.setAppShareContent(appShareContent);
            //分享Icon
            param=new Param();
            param.setKv("share_icon");
            param=paramService.getParamByEntity(param);
            outDevice.setShareIcon(param.getValue());
            //app分享title
            param=new Param();
            param.setKv("share_title");
            param=paramService.getParamByEntity(param);
            String shareTitle=param.getValue();
            shareTitle=shareTitle.replace("${useTime}",String.valueOf(useTime));
            outDevice.setShareTitle(shareTitle);
            //分享内容
            param=new Param();
            param.setKv("share_content");
            param=paramService.getParamByEntity(param);
            String  shareContent=param.getValue();
            shareContent=shareContent.replace("${tar}",String.valueOf( outDevice.getTar()));
            outDevice.setShareContent(shareContent);
            //分享url
            param=new Param();
            param.setKv("share_url");
            param=paramService.getParamByEntity(param);
            outDevice.setShareUrl(param.getValue());
            retObj.setData(outDevice);
            retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
        } else {
            retObj.setCode(Status.INVALID.getIndex());
            retObj.setMsg(requestContext.getMessage("sys.device.noexist"));
        }

        return retObj;

    }

    /**
     * 商城入口
     * @param
     *
     * @return  RetObj json
     */
    @ResponseBody
    @RequestMapping(value = "/mall")
    @Config(methods = "mall",module = "设备模块",needlogin =true,interfaceLog =true)
    public RetObj mall(HttpServletRequest request)throws Exception {
        RetObj retObj = new RetObj();
        RequestContext requestContext = new RequestContext(request);
        Param  param=null;
        OutMall outMall=new OutMall();
        //商城Icon
         param=new Param();
         param.setKv("mall_icon");
         param=paramService.getParamByEntity(param);
         outMall.setMallIcon(param.getValue());
         //商城title
         param=new Param();
        param.setKv("mall_title");
        param=paramService.getParamByEntity(param);
        outMall.setMallTitle(param.getValue());
        //分享内容
        param=new Param();
        param.setKv("mall_content");
        param=paramService.getParamByEntity(param);
        outMall.setMallContent(param.getValue());
        retObj.setData( outMall);

        retObj.setMsg(requestContext.getMessage("sys.prompt.success"));

        return retObj;

    }

    /**
     * 使用记录
     * @param
     *
     * @return  RetObj json
     */
    @ResponseBody
    @RequestMapping(value = "/record")
    @Config(methods = "recor",module = "设备模块",needlogin =true,interfaceLog =true)
    public RetObj recor(@JsonParam InRecord inRecord,HttpServletRequest request)throws Exception
    {
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        Device device=new Device();
        device.setId(inRecord.getId());
        CustomerDevice customerDevice=new CustomerDevice();
        customerDevice.setDevice(device);
        customerDevice.setCustomer(getCurrentCustomer(request));
        CustomerDevice  cd=customerDeviceService.getCustomerDeviceByEntity(customerDevice);
        List<Record> list= new ArrayList<Record>();
        if(cd!=null){
           Record r=new Record();
           r.setCustomerDeviceId(cd.getId());
           List<Record> hlist=recordService.getRecordPageByEntity(r);

           for(Date  userTime:inRecord.getUseTime()) {
               boolean flag=true;
               if( hlist!=null){
                   for (Record hRecord : hlist) {
                      if(hRecord.getUseTime().getTime()==userTime.getTime()){
                          flag=false;
                      }
                   }
               }
               if(flag){
                   Record record=new Record();
                   record.setId(IdGenerator.getId());
                   record.setUseTime(userTime);
                   record.setCustomerDeviceId(cd.getId());
                   list.add(record);
               }
           }
            for(Record record:list){
                recordService.addRecord(record);

            }
            retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
       }else{
           retObj.setCode(Status.INVALID.getIndex());
           retObj.setMsg(requestContext.getMessage("sys.device.noexist"));
       }

        return retObj;

    }

    public ICustomerDeviceService getCustomerDeviceService() {
        return customerDeviceService;
    }

    public void setCustomerDeviceService(ICustomerDeviceService customerDeviceService) {
        this.customerDeviceService = customerDeviceService;
    }

    public IRecordService getRecordService() {
        return recordService;
    }

    public void setRecordService(IRecordService recordService) {
        this.recordService = recordService;
    }

    public IDeviceService getDeviceService() {
        return deviceService;
    }

    public void setDeviceService(IDeviceService deviceService) {
        this.deviceService = deviceService;
    }

    public IParamService getParamService() {
        return paramService;
    }

    public void setParamService(IParamService paramService) {
        this.paramService = paramService;
    }

    public IStatisticsService getStatisticsService() {
        return statisticsService;
    }

    public void setStatisticsService(IStatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    public static  void  main(String args[]){
     System.out.println(DateUtil.convert2Date("2017-12-4 16:11:11", "yyyy-MM-dd HH:mm:ss").getTime());
   }
}
