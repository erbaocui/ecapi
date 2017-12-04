package com.cn.service;

import com.cn.model.Device;

import java.util.List;

public interface IDeviceService {
    //用户

    public List<Device> getDevicePageByEntity(Device device);
    public Device getDeviceByEntity(Device device);
    public void addDevice(Device device);
    public void modifyDevice(Device device);

}
