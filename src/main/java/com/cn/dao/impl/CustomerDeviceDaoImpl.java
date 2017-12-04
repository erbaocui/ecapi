package com.cn.dao.impl;

import com.cn.dao.ICustomerDeviceDao;
import com.cn.model.CustomerDevice;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("customerDeviceDao")
public class CustomerDeviceDaoImpl extends BaseDaoImpl implements ICustomerDeviceDao {

    @Override
    public List<CustomerDevice> pageList(CustomerDevice customerDevice) {
        return (List<CustomerDevice>)list("com.cn.dao.CustomerDeviceMapper.selectPageByEntity", customerDevice);

    }

    @Override
    public void insert(CustomerDevice customerDevice) {
        addObject("com.cn.dao.CustomerDeviceMapper.insert", customerDevice);
    }

    @Override
    public void update(CustomerDevice customerDevice) {
        updateObject("com.cn.dao.CustomerDeviceMapper.update", customerDevice);
    }

    @Override

    public CustomerDevice find(CustomerDevice customerDevice) {
        return (CustomerDevice)findObject("com.cn.dao.CustomerDeviceMapper.selectOneByEntity", customerDevice);
    }
}
