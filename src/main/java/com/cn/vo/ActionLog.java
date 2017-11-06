package com.cn.vo;



import java.util.Date;

/**
 * Created by home on 2017/11/1.
 */
public class ActionLog {


    /**
     * 日志id
     */
    private String  id;

    /**
     * 当前操作人token
     */
    private String token;
    private String loginId;
    private String loginName;
    private String requestParam;
    private String responseParam;

    /**
     * 当前操作人ip
     */
    private String loginIp;

    /**
     * 操作请求的链接
     */
    private String actionUrl;

    /**
     * 执行的模块
     */
    private String module;

    /**
     * 执行的方法
     */
    private String method;

    /**
     * 执行操作时间
     */
    private Date actionTime;

    /**
     * 描述
     */
    private String description;

    /**
     * 执行的时间
     */
    private String  executeTime;



    /**
     * 该操作状态 0表示成功，1表示失败！,-1标识不需要记录
     */
    private Integer status=-1;
    /**
     * 错误堆栈
     */
    private String errorStack;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public String getResponseParam() {
        return responseParam;
    }

    public void setResponseParam(String responseParam) {
        this.responseParam = responseParam;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public String getErrorStack() {
        return errorStack;
    }

    public void setErrorStack(String errorStack) {
        this.errorStack = errorStack;
    }
}
