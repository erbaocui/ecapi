package com.cn.service.impl;

import com.cn.dao.ICustomerMessageDao;
import com.cn.model.Customer;
import com.cn.model.CustomerMessage;
import com.cn.service.ICustomerMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("customerMessageService")
public class CustomerMessageService implements ICustomerMessageService {


    @Autowired
    private ICustomerMessageDao customerMessageDao;



    @Override
    public List<CustomerMessage> getCustomerMessagePageByEntity(CustomerMessage customerMessage){
        return customerMessageDao.pageList(customerMessage);
    }
    @Override
    public CustomerMessage getCustomerMessageByEntity(CustomerMessage customerMessage) {
        return customerMessageDao.find(customerMessage);
    }
    @Override
    public void addCustomerMessage(CustomerMessage customerMessage) {
        customerMessageDao.insert(customerMessage);
    }

    @Override
    public void modifyCustomerMessage(CustomerMessage customerMessage) {
        customerMessageDao.update(customerMessage);
    }

    @Override
    public void deleteCustomerMessage(CustomerMessage customerMessage) {
        customerMessageDao.delete(customerMessage);
    }

    public ICustomerMessageDao getCustomerMessageDao() {
        return customerMessageDao;
    }

    public void setCustomerMessageDao(ICustomerMessageDao customerMessageDao) {
        this.customerMessageDao = customerMessageDao;
    }

}
