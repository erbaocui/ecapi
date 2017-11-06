package com.cn.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

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
        ModelAndView mv = new ModelAndView();
        try {

            FastJsonJsonView view = new FastJsonJsonView();

            OutputStream out =  httpServletResponse.getOutputStream();
            Map<String,String> temp=new HashMap();

            if(e instanceof  Exception) {
                temp.put("msg","测试错误异常处理");
            }/*else if(ex instanceof ParameterException) {
                return new ModelAndView("error-parameter", model);
            } else {
                return new ModelAndView("error", model);
            }*/
            errorlogger.error(e.getMessage(),e);
            view.setAttributesMap(temp);
            mv.setView(view);
        }catch(Exception e1) {
            errorlogger.error(e1.getMessage(),e1);
        }finally {
            return mv;
        }
    }
}
