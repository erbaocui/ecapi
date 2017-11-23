package com.cn.service;

import com.cn.model.Customer;

import java.util.List;

public interface ICustomerService {
    //用户
    public List<Customer> getCustomerPageByEntity(Customer customer);
    public Customer getCustomerByEntity(Customer customer);
    public void addCustomer(Customer customer);
    public void modifyCustomer(Customer customer);

}
