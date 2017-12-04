package com.cn.dao.impl;

import com.cn.dao.IDeviceDao;
import com.cn.model.Device;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("deviceDao")
public class DeviceDaoImpl extends BaseDaoImpl implements IDeviceDao {

    @Override
    public List<Device> pageList(Device device) {
        return (List<Device>)list("com.cn.dao.DeviceMapper.selectPageByEntity", device);

    }

    @Override
    public void insert(Device device) {
        addObject("com.cn.dao.DeviceMapper.insert", device);
    }

    @Override
    public void update(Device device) {
        updateObject("com.cn.dao.DeviceMapper.updateByPrimaryKey", device);
    }

    @Override
    public Device find(Device device) {
        return (Device)findObject("com.cn.dao.DeviceMapper.selectOneByEntity", device);
    }
}
