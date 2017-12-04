package com.cn.service.impl;

import com.cn.dao.IDeviceDao;
import com.cn.model.Device;
import com.cn.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("deviceService")
public class DeviceService implements IDeviceService {


    @Autowired
    private IDeviceDao deviceDao;



    @Override
    public List<Device> getDevicePageByEntity(Device device){
        return deviceDao.pageList(device);
    }
    @Override
    public Device getDeviceByEntity(Device device) {
        return deviceDao.find(device);
    }
    @Override
    public void addDevice(Device device) {
        deviceDao.insert(device);
    }

    @Override
    public void modifyDevice(Device device) {
        deviceDao.update(device);
    }



    public IDeviceDao getDeviceDao() {
        return deviceDao;
    }

    public void setDeviceDao(IDeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

}
