package com.cn.controller;

import com.cn.annotation.Config;
import com.cn.annotation.JsonParam;
import com.cn.constant.PicType;
import com.cn.model.Commodity;
import com.cn.model.Customer;
import com.cn.model.Message;
import com.cn.model.Picture;
import com.cn.param.*;

import com.cn.service.ICustomerMessageService;
import com.cn.service.IMessageService;
import com.cn.service.IPictureService;
import com.cn.vo.MessageEx;
import com.cn.vo.RetObj;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/message")
@Scope("prototype")
public class MessageController extends BaseController{

    Logger logger= Logger.getLogger(MessageController.class.getName());

    @Autowired
    private IMessageService messageService;




    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }


    /**
     * 消息列表查询
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/list")
    @Config(methods = "list",module = "消息模块",needlogin = true,interfaceLog =true)
    public @ResponseBody
    RetObj list(@JsonParam InMessageList inMessageList,HttpServletRequest request)throws Exception
    {
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        PageHelper.startPage(inMessageList.getPage()+1 ,inMessageList.getSize());
        Customer customer=getCurrentCustomer(request);
        if(inMessageList.getPage().equals(0)){
            messageService.modifyCustomerMessageHavenRead(customer);
        }

        MessageEx message=new MessageEx();
        message.setStatus("0");
        List<OutMessageList> outList=new ArrayList<OutMessageList>();
        List<Message> list=messageService.getMessagePageByEntity(message);

        for(Message msg:list){
            OutMessageList outMessageList=new OutMessageList();
            outMessageList.setId(msg.getId());
            outMessageList.setSendTime(msg.getSendTime());
            outMessageList.setTitle(msg.getTitle());
            outList.add( outMessageList);
        }

        //PageInfo<Message> p=new PageInfo<Message>(list);

        retObj.setData(outList);
        retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
        return retObj;
    }

    /**
     * 消息详情
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/info")
    @Config(methods = "info",module = "消息模块",needlogin = true,interfaceLog =true)
    public @ResponseBody
    RetObj list(@JsonParam InMessageInfo  inMessageInfo,HttpServletRequest request)throws Exception
    {
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);

        MessageEx message=new MessageEx();
        message.setId(inMessageInfo.getId());
        Message msg =messageService.getMessageByEntity(message);
        OutMessageInfo outMessageInfo=new   OutMessageInfo();
        BeanUtils.copyProperties(outMessageInfo, msg);
        retObj.setData(outMessageInfo);
        retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
        return retObj;
    }

    /**
     * 未读消息数量
     * @param
     *
     * @return listMenu json
     */
    @RequestMapping(value = "/unreadCount")
    @Config(methods = "unreadCount",module = "消息模块",needlogin = true,interfaceLog =true)
    public @ResponseBody
    RetObj unreadCount(HttpServletRequest request)throws Exception
    {
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        Customer customer=getCurrentCustomer(request);
        int  unreadCount=messageService.getMessageUnreadCount(customer);
        OutMessageUnreadCount outMessageUnreadCount=new  OutMessageUnreadCount();
        outMessageUnreadCount.setUnreadCount(unreadCount);
        retObj.setData(outMessageUnreadCount);
        retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
        return retObj;
    }


    public IMessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(IMessageService messageService) {
        this.messageService = messageService;
    }
}
