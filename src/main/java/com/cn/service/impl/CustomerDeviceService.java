package com.cn.service.impl;

import com.cn.dao.ICustomerDeviceDao;
import com.cn.dao.IDeviceDao;
import com.cn.model.Customer;
import com.cn.model.CustomerDevice;
import com.cn.model.Device;
import com.cn.service.ICustomerDeviceService;
import com.cn.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("customerDeviceService")
public class CustomerDeviceService implements ICustomerDeviceService {


    @Autowired
    private ICustomerDeviceDao customerDeviceDao;
    @Autowired
    private IDeviceDao deviceDao;



    @Override
    public List<CustomerDevice> getCustomerDevicePageByEntity(CustomerDevice customerDevice){
        return customerDeviceDao.pageList(customerDevice);
    }
    @Override
    public CustomerDevice getCustomerDeviceByEntity(CustomerDevice customerDevice) {
        return customerDeviceDao.find(customerDevice);
    }
    @Override
    public void addCustomerDevice(CustomerDevice customerDevice) {
        customerDeviceDao.insert(customerDevice);
    }

    @Override
    public void modifyCustomerDevice(CustomerDevice customerDevice) {
        customerDeviceDao.update(customerDevice);
    }
    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class},timeout=10,isolation= Isolation.SERIALIZABLE)
    public void bindCustomerDevice(CustomerDevice customerDevice) {

        Device device=new Device();
        device.setId(customerDevice.getDevice().getId());
        Device d=deviceDao.find(device);
        if(d==null) {
            device.setName(customerDevice.getDevice().getName());
            deviceDao.insert(device);
        }
        CustomerDevice cd=customerDeviceDao.find( customerDevice);
        if(cd==null){
            customerDevice.setId(IdGenerator.getId());
            customerDevice.setStatus("0");
            customerDevice.setCreateTime(new Date());
            customerDeviceDao.insert(customerDevice);
        }else if(cd.getStatus().equals("1")){
            cd=new CustomerDevice();
            cd.setStatus("1");
            cd.setDevice(device);
            customerDeviceDao.update(cd);
            cd.setCustomer(customerDevice.getCustomer());
            cd.setStatus("0");
            customerDeviceDao.update(cd);
        }
    }

    @Override
    public void unbindCustomerDevice(CustomerDevice customerDevice) {
        customerDevice.setStatus("1");
        customerDeviceDao.update(customerDevice);
    }


    public ICustomerDeviceDao getCustomerDeviceDao() {
        return customerDeviceDao;
    }

    public void setCustomerDeviceDao(ICustomerDeviceDao customerDeviceDao) {
        this.customerDeviceDao = customerDeviceDao;
    }

    public IDeviceDao getDeviceDao() {
        return deviceDao;
    }

    public void setDeviceDao(IDeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }
}
