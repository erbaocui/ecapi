package com.cn.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.cn.constant.Status;
import com.cn.vo.RetObj;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.util.WebUtils;

/**
 * Created by home on 2017/11/1.
 */
@Component("exceptionHandler")

public class CustomExceptionResolver implements HandlerExceptionResolver {
    private org.apache.log4j.Logger errorlogger= org.apache.log4j.Logger.getLogger("Error");
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView  modelAndView=new ModelAndView();
        try {
            RequestContext requestContext=new RequestContext(httpServletRequest);
            RetObj retObj=new RetObj();
            retObj.setCode(Status.INVALID.getIndex());
            retObj.setMsg(requestContext.getMessage("sys.prompt.exception"));
            String data =  JSON.toJSONString(retObj);
            /*httpServletResponse.setStatus(555);
            httpServletRequest.setAttribute("javax.servlet.error.status_code",555);
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json; charset=utf-8");
            OutputStream out =  httpServletResponse.getOutputStream();
            String data =  JSON.toJSONString(retObj);
            out.write(data.getBytes("UTF-8"));*/
            modelAndView.addObject("data", data);
                    modelAndView.setViewName("/error");
            errorlogger.error(e.getMessage(),e);

        }catch(Exception e1) {
            errorlogger.error(e1.getMessage(),e1);
        }finally {
            return modelAndView;
        }
    }
}
