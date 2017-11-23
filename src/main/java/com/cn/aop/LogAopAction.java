package com.cn.aop;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.cn.annotation.Config;
import com.cn.constant.Status;
import com.cn.controller.token.TokenManager;
import com.cn.controller.token.TokenModel;
import com.cn.model.Customer;
import com.cn.service.IEsService;
import com.cn.util.DateUtil;
import com.cn.vo.ActionLog;
import com.cn.vo.RetObj;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 草帽boy on 2017/2/21.
 */
@Component
@Aspect
public class LogAopAction {
    private Logger logger= Logger.getLogger("Interface");


    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private IEsService esService;



    @Pointcut("execution(* com.cn.controller..*.*(..))")
    private void controllerAspect(){}

    /**
     * 方法开始执行
     */
    @Before("controllerAspect()")
    public void doBefore(){
        //System.out.println("开始");
    }

    /**
     * 方法结束执行
     */
    @After("controllerAspect()")
    public void after(){


        //System.out.println("结束");
    }

    /**
     * 方法结束执行后的操作
     */
    @AfterReturning("controllerAspect()")
    public void doAfter() throws Exception{

    }

    /**
     * 方法有异常时的操作
     */
    @AfterThrowing("controllerAspect()")
    public void doAfterThrow(){
    }


    /**
     * 方法执行
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        //日志实体对象
        ActionLog log = new ActionLog();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // 拦截的实体类，就是当前正在执行的controller
        Object target = pjp.getTarget();
        // 拦截的方法名称。当前正在执行的方法
        String methodName = pjp.getSignature().getName();
        // 拦截的方法参数
        Object[] args = pjp.getArgs();
        // 拦截的放参数类型
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Class[] parameterTypes = msig.getMethod().getParameterTypes();

        Object object = null;

        Method method = null;
        try {
            method = target.getClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (null != method) {
            // 判断是否包含自定义的注解，说明一下这里的SystemLog就是我自己自定义的注解
            Annotation methodConfigAnnotation = method.getAnnotation(Config.class);
            if (methodConfigAnnotation != null) {
                Config config = method.getAnnotation(Config.class);
                boolean interfaceLog = config.interfaceLog();
                if (interfaceLog) {
                    String token=(String) request.getHeader("token");
                    TokenModel tokenModel=null;
                    Customer customer=null;
                    if(token!=null&&(!token.equals(""))){
                        tokenModel= tokenManager.getToken(token);
                    }
                    if( tokenModel!=null){
                        customer=tokenModel.getCustomer();
                    }
                    if(customer!=null){
                        log.setLoginId(customer.getId());
                        log.setLoginName(customer.getLoginName());
                        log.setToken(token);
                    }
                    Map requestMap= requestParams(request);
                    log.setRequestParam(JSON.toJSONString(requestMap).toString());
                    log.setModule(config.module());
                    log.setMethod(config.methods());
                    //log.setLoginIp(getIp(request));
                    log.setActionUrl(request.getRequestURI());
                    long beginTime = new Date().getTime();
                    long endTime=0;
                    try {
                        log.setActionTime( DateUtil.setTimeZone(new Date(beginTime)));
                        object = pjp.proceed();
                        endTime = new Date().getTime();
                        log.setDescription("执行成功");
                        log.setExecuteTime(String.valueOf(endTime - beginTime));
                        log.setStatus(0);
                        log.setResponseParam(JSON.toJSONString(object).toString());
                        writeLog(log);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block;
                        endTime = new Date().getTime();
                        log.setExecuteTime(String.valueOf(endTime - beginTime));
                        log.setDescription("执行失败");
                        log.setStatus(1);
                        //log.setErrorStack(e.getStackTrace().toString());
                        //String errorStack="";

                        String fullStackTrace = ExceptionUtils.getStackTrace(e);
                        log.setErrorStack(fullStackTrace);
                        RequestContext requestContext=new RequestContext(request);
                        RetObj retObj=new RetObj();
                        retObj.setMsg(requestContext.getMessage("sys.prompt.fail"));
                        retObj.setCode(Status.INVALID.getIndex());
                        log.setResponseParam(JSON.toJSONString(retObj).toString());
                        writeLog(log);
                        throw e;

                    }
                }else{ object = pjp.proceed();}

            }else{ object = pjp.proceed();}
          } else{
            object = pjp.proceed();
           } /*else {//没有包含注解
                object = pjp.proceed();
                log.setDescription("此操作不包含注解");
                log.setState((short)0);
            }
        } else { //不需要拦截直接执行
            object = pjp.proceed();
            log.setDescription("不需要拦截直接执行");
            log.setState((short)0);
        }*/
        return object;
    }

    /**
     * 获取ip地址
     * @param request
     * @return
     */
    private String getIp(HttpServletRequest request){
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

  /*  public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }
*/
    public TokenManager getTokenManager() {
        return tokenManager;
    }

    public void setTokenManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    private void writeLog(ActionLog log){

        Integer state=log.getStatus();
        if(state!=null&&state!=-1){
            logger.info(JSON.toJSONString(log));
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

}