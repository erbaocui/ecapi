package com.cn.dao;

import com.cn.model.CustomerMessage;


import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface ICustomerMessageDao {


    public List<CustomerMessage> pageList(CustomerMessage customerMessage);
    public int count(CustomerMessage customerMessage);
    public void insert(CustomerMessage customerMessage);
    public void update(CustomerMessage customerMessage);
    public CustomerMessage find(CustomerMessage customerMessage);
    public void delete(CustomerMessage customerMessage);


}