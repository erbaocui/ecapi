package com.cn.controller;

import com.cn.annotation.Config;
import com.cn.annotation.JsonParam;
import com.cn.controller.token.TokenManager;
import com.cn.controller.token.TokenModel;
import com.cn.model.Customer;
import com.cn.model.Feedback;
import com.cn.param.InFeedBack;
import com.cn.service.IFeedbackService;
import com.cn.util.DateUtil;

import com.cn.util.IdGenerator;
import com.cn.vo.FeedbackEx;
import com.cn.vo.RetObj;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * Created by home on 2017/6/27.
 */

@Controller
@RequestMapping("/feedback")
@Scope("prototype")
public class FeedbackController extends BaseController{

    Logger logger= Logger.getLogger(FeedbackController.class.getName());

    @Autowired
    private IFeedbackService feedbackService;

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }







    /**
     * 客户编辑
     * @param
     *
     * @return map json
     */
    @ResponseBody
    @RequestMapping(value = "/add")
    @Config(methods = "add",module = "意见反馈",needlogin = true,interfaceLog =true)
    public RetObj add(@JsonParam InFeedBack inFeedBack,HttpServletRequest request)throws Exception
    {
        RetObj retObj=new RetObj();
        RequestContext requestContext=new RequestContext(request);
        Feedback fb=new Feedback();
        fb.setId(IdGenerator.getId());
        fb.setContact(inFeedBack.getContact());
        fb.setStatus("0");
        Customer customer=getCurrentCustomer(request);
        fb.setFeedback(inFeedBack.getFeedback());
        fb.setLoginId( customer.getLoginName());
        fb.setCustomerId(customer.getId());
        fb.setCreateTime(new Date());
        feedbackService.addFeedback(fb);
        retObj.setMsg(requestContext.getMessage("sys.prompt.success"));
        return retObj;

    }



    public IFeedbackService getFeedbackService() {
        return feedbackService;
    }

    public void setFeedbackService(IFeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }
}
