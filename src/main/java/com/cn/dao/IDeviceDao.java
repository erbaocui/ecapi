package com.cn.dao;

import com.cn.model.Device;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface IDeviceDao {


    public List<Device> pageList(Device device);
    public void insert(Device device);
    public void update(Device device);
    public Device find(Device device);


}