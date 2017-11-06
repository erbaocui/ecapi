package com.cn.service;

import com.cn.model.AppLogin;
import com.cn.model.Customer;

import java.util.List;

public interface ICustomerService {
    //用户
    public List<Customer> getCustomerPageByEntity(Customer customer);
    public List<Customer> getAllValidCustomer(String type);
    public Customer getCustomerByEntity(Customer customer);
    public void addCustomer(Customer customer);
    public void modifyCustomer(Customer customer);
    //客户登录
    public void addApploginCustomer( AppLogin appLogin,AppLogin lastAppLogin) throws Exception;
    public  AppLogin getLastApploginByEntity(AppLogin appLogin);

}
