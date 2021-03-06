package com.cn.model;

import java.util.Date;

public class AppLogin {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_app_login.Id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_app_login.token_id
     *
     * @mbggenerated
     */
    private String tokenId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_app_login.customer_id
     *
     * @mbggenerated
     */
    private String customerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_app_login.customer_name
     *
     * @mbggenerated
     */
    private String customerName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_app_login.type
     *
     * @mbggenerated
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_app_login.status
     *
     * @mbggenerated
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_app_login.login_time
     *
     * @mbggenerated
     */
    private Date loginTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_app_login.device_id
     *
     * @mbggenerated
     */
    private String deviceId;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_app_login
     *
     * @mbggenerated
     */
    public AppLogin(String id, String tokenId, String customerId, String customerName, String type, String status, Date loginTime, String deviceId) {
        this.id = id;
        this.tokenId = tokenId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.type = type;
        this.status = status;
        this.loginTime = loginTime;
        this.deviceId = deviceId;
    }

    public AppLogin() {
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_app_login.Id
     *
     * @return the value of t_app_login.Id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_app_login.token_id
     *
     * @return the value of t_app_login.token_id
     *
     * @mbggenerated
     */
    public String getTokenId() {
        return tokenId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_app_login.customer_id
     *
     * @return the value of t_app_login.customer_id
     *
     * @mbggenerated
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_app_login.customer_name
     *
     * @return the value of t_app_login.customer_name
     *
     * @mbggenerated
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_app_login.type
     *
     * @return the value of t_app_login.type
     *
     * @mbggenerated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_app_login.status
     *
     * @return the value of t_app_login.status
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_app_login.login_time
     *
     * @return the value of t_app_login.login_time
     *
     * @mbggenerated
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_app_login.device_id
     *
     * @return the value of t_app_login.device_id
     *
     * @mbggenerated
     */
    public String getDeviceId() {
        return deviceId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

}