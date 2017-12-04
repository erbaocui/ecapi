package com.cn.controller;

import com.cn.annotation.Config;
import com.cn.annotation.JsonParam;


import com.cn.constant.PicType;
import com.cn.constant.Status;

import com.cn.model.*;

import com.cn.param.*;
import com.cn.service.ICustomerDeviceService;
import com.cn.service.ICustomerService;
import com.cn.service.IDeviceService;
import com.cn.service.IRecordService;
import com.cn.three.sms.SmsVerifyKit;
import com.cn.util.DateUtil;
import com.cn.util.IdGenerator;
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
            outBindDevice.setId(cd.getId());
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
    public RetObj info(@JsonParam InDevice inDevice,HttpServletRequest request)throws Exception
    {
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        Device device=new Device();
        device.setId(inDevice.getId());
        CustomerDevice customerDevice=new CustomerDevice();
        customerDevice.setDevice(device);
        customerDevice.setCustomer(getCurrentCustomer(request));

        CustomerDevice  cd=customerDeviceService.getCustomerDeviceByEntity(customerDevice);
        if(cd!=null){
            OutDevice outDevice=new OutDevice();
            outDevice.setName(cd.getDevice().getName());
            outDevice.setId(cd.getId());
            outDevice.setLon(cd.getDevice().getLon());
            outDevice.setLat(cd.getDevice().getLat());
            outDevice.setUseCount(cd.getUseCount());
            outDevice.setChargeCount(cd.getChargeCount());
            outDevice.setCo(cd.getUseCount());
            outDevice.setTar(cd.getUseCount());
            retObj.setData(outDevice);
            retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
        }else{
            retObj.setCode(Status.INVALID.getIndex());
            retObj.setMsg(requestContext.getMessage("sys.device.noexist"));
        }

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

   public static  void  main(String args[]){
     System.out.println(DateUtil.convert2Date("2017-12-4 16:11:11", "yyyy-MM-dd HH:mm:ss").getTime());
   }
}
