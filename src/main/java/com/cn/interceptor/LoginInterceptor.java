package com.cn.interceptor;

/**
 * Created by home on 2017/6/28.
 */

import com.alibaba.fastjson.JSON;
import com.cn.annotation.Config;
import com.cn.constant.Status;
import com.cn.controller.token.TokenManager;
import com.cn.vo.RetObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录认证的拦截器
 */
public class LoginInterceptor implements HandlerInterceptor{

    @Autowired
    private TokenManager tokenManager;

    /**
     * Handler执行完成之后调用这个方法
     */
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exc)
            throws Exception {

    }

    /**
     * Handler执行之后，ModelAndView返回之前调用这个方法
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * Handler执行之前调用这个方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        RequestContext requestContext=new RequestContext(request);
        String token=(String) request.getHeader("token");
        if (handler instanceof HandlerMethod) {
            HandlerMethod myHandlerMethod = (HandlerMethod) handler;
            Object bean = myHandlerMethod.getBean();
            Method method= myHandlerMethod.getMethod();
           // Annotation classLoginAnnotation = bean.getClass().getAnnotation(RequireLogin.class);//类上有该标记
            Annotation methodConfigAnnotation=method.getAnnotation(Config.class);

            boolean needlogin=true;
           if( methodConfigAnnotation!=null) {
               Config config=method.getAnnotation(Config.class);//方法上有该标记
               needlogin = config.needlogin();
           }
            if( needlogin)
            {
                boolean isLogin =tokenManager.checkToken(token);
                if(isLogin)
                    return true;
                else{//未登录

                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json; charset=utf-8");
                    OutputStream out = response.getOutputStream();
                    RetObj retObj=new RetObj();
                    retObj.setCode(Status.INVALID.getIndex());
                    retObj.setMsg(requestContext.getMessage("sys.prompt.needlogin"));
                    String data =  JSON.toJSONString(retObj);
                    out.write(data.getBytes("UTF-8"));
                       return false;
                }//IF LOGIN END
            }//if Annotation end
        }
        return true;
    }
    private boolean isAjax(HttpServletRequest request){
        String requestType = request.getHeader("X-Requested-With");
        if (requestType != null && requestType.equals("XMLHttpRequest")) {
            return true;
        }
        return false;
    }

}