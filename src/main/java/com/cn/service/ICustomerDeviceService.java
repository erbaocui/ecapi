package com.cn.service;

import com.cn.model.CustomerDevice;
import com.cn.model.Device;

import java.util.List;

public interface ICustomerDeviceService {
    //用户
    public List<CustomerDevice> getCustomerDevicePageByEntity(CustomerDevice customerDevice);
    public CustomerDevice getCustomerDeviceByEntity(CustomerDevice customerDevice);
    public void addCustomerDevice(CustomerDevice customerDevice);
    public void modifyCustomerDevice(CustomerDevice customerDevice);
    public void bindCustomerDevice(CustomerDevice customerDevice);
    public void unbindCustomerDevice(CustomerDevice customerDevice);

}
