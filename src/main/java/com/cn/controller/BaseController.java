package com.cn.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by home on 2017/6/27.
 */
public abstract  class  BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static ThreadLocal<ServletRequest> currentRequest = new ThreadLocal<ServletRequest>();
    private static ThreadLocal<ServletResponse> currentResponse = new ThreadLocal<ServletResponse>();

    private org.apache.log4j.Logger errorlogger= org.apache.log4j.Logger.getLogger("Error");


    @ModelAttribute
    public void initReqAndRep(HttpServletRequest request, HttpServletResponse response) {
        currentRequest.set(request);
        currentResponse.set(response);
    }

  /*@ExceptionHandler (value=Exception.class)
    public String exp(HttpServletRequest request, HttpServletResponse response,Exception ex) {

        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            OutputStream out =  response.getOutputStream();
            Map<String,String> temp=new HashMap();
            temp.put("msg","测试错误异常处理");


            if(ex instanceof  Exception) {
                temp.put("msg","测试错误异常处理");
            }*//*else if(ex instanceof ParameterException) {
                return new ModelAndView("error-parameter", model);
            } else {
                return new ModelAndView("error", model);
            }*//*
            errorlogger.error(ex.getMessage(),ex);
            String data =  JSON.toJSONString(temp);
            out.write(data.getBytes("UTF-8"));


        } catch (IOException e1) {
            e1.printStackTrace();
        }finally {
            return null;
        }
    }*/

    public HttpServletRequest request() {
        return (HttpServletRequest) currentRequest.get();
    }


    public HttpServletResponse response() {
        return (HttpServletResponse) currentResponse.get();
    }



}
