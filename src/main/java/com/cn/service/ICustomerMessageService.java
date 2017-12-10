package com.cn.service;

import com.cn.model.Customer;
import com.cn.model.CustomerMessage;

import java.util.List;

public interface ICustomerMessageService {
    //用户
    public List<CustomerMessage> getCustomerMessagePageByEntity(CustomerMessage customerMessage);
    public CustomerMessage getCustomerMessageByEntity(CustomerMessage customerMessage);
    public void addCustomerMessage(CustomerMessage customerMessage);
    public void modifyCustomerMessage(CustomerMessage customerMessage);
    public void deleteCustomerMessage(CustomerMessage customerMessage);


}
