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

/**
 * Created by home on 2017/11/1.
 */
@Component("exceptionHandler")
public class CustomExceptionResolver implements HandlerExceptionResolver {
    private org.apache.log4j.Logger errorlogger= org.apache.log4j.Logger.getLogger("Error");
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        RetObj retObj=new RetObj();
        try {

            RequestContext requestContext=new RequestContext(httpServletRequest);
            retObj.setMsg(requestContext.getMessage("sys.prompt.fail"));
            retObj.setCode(Status.INVALID.getIndex());
            errorlogger.error(e.getMessage(),e);
            httpServletResponse.getWriter().print(retObj.toString());

        }catch(Exception e1) {
            errorlogger.error(e1.getMessage(),e1);
        }finally {
            return null;
        }
    }
}
