package com.cn.dao;

import com.cn.model.CustomerDevice;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface ICustomerDeviceDao {


    public List<CustomerDevice> pageList(CustomerDevice customerDevice);
    public void insert(CustomerDevice customerDevice);
    public void update(CustomerDevice customerDevice);
    public CustomerDevice find(CustomerDevice customerDevice);


}