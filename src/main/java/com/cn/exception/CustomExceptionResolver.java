package com.cn.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.cn.constant.Status;
import com.cn.controller.token.TokenManager;
import com.cn.controller.token.TokenModel;
import com.cn.model.Customer;
import com.cn.service.IEsService;
import com.cn.service.impl.EsService;
import com.cn.util.DateUtil;
import com.cn.vo.ActionLog;
import com.cn.vo.RetObj;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.util.WebUtils;

/**
 * Created by home on 2017/11/1.
 */
@Component("exceptionHandler")

public class CustomExceptionResolver implements HandlerExceptionResolver, ApplicationContextAware {
    private org.apache.log4j.Logger errorlogger= org.apache.log4j.Logger.getLogger("Error");
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this .applicationContext=applicationContext;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView  modelAndView=new ModelAndView();
        try {
            RequestContext requestContext=new RequestContext(httpServletRequest);
            RetObj retObj=new RetObj();
            retObj.setCode(Status.INVALID.getIndex());
            retObj.setMsg(requestContext.getMessage("sys.prompt.exception"));

            /*httpServletResponse.setStatus(555);
            httpServletRequest.setAttribute("javax.servlet.error.status_code",555);
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json; charset=utf-8");
            OutputStream out =  httpServletResponse.getOutputStream();
            String data =  JSON.toJSONString(retObj);
            out.write(data.getBytes("UTF-8"));*/
            if(e.getClass().equals(ParamParseException.class)){

                ActionLog log = new ActionLog();
                log.setLoginIp(getIpAddr(httpServletRequest));
                Map requestMap= requestParams(httpServletRequest);
                log.setRequestParam(JSON.toJSONString(requestMap).toString());

                //log.setLoginIp(getIp(request));
                log.setActionUrl(httpServletRequest.getRequestURI());
                long beginTime = new Date().getTime();
                long endTime=0;
                log.setActionTime( DateUtil.setTimeZone(new Date(beginTime)));
                endTime = new Date().getTime();
                log.setExecuteTime(String.valueOf(endTime - beginTime));
                log.setDescription("执行失败");
                log.setStatus(1);
                //log.setErrorStack(e.getStackTrace().toString());
                //String errorStack="";

                String fullStackTrace = ExceptionUtils.getStackTrace(e);
                log.setErrorStack(fullStackTrace);

                retObj=new RetObj();
                retObj.setMsg(requestContext.getMessage("sys.prompt.paramerror"));
                retObj.setCode(Status.INVALID.getIndex());
                log.setResponseParam(JSON.toJSONString(retObj).toString());
                writeLog(log);
            }
            String data =  JSON.toJSONString(retObj);
            modelAndView.addObject("data", data);
            modelAndView.setViewName("/error");
            errorlogger.error(e.getMessage(),e);

        }catch(Exception e1) {
            errorlogger.error(e1.getMessage(),e1);
        }finally {
            return modelAndView;
        }
    }


    private void writeLog(ActionLog log){

        Integer state=log.getStatus();
        if(state!=null&&state!=-1){
            errorlogger.info(JSON.toJSONString(log));
            EsService esService=(EsService)applicationContext.getBean("esService");
            esService.createActionLog(log);

        }
    }

    private Map<String,Object> requestParams(HttpServletRequest request) {
        Map<String,Object> map = new HashMap<String,Object>();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();

            Object[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                Object paramValue = paramValues[0];
                if (paramValue!=null) {
                    map.put(paramName, paramValue);
                }
            }
        }
        return  map;

    }

    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if(null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
