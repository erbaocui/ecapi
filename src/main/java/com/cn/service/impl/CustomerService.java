package com.cn.service.impl;

import com.cn.constant.Status;
import com.cn.dao.IAppLoginDao;
import com.cn.dao.ICustomerDao;
import com.cn.model.AppLogin;
import com.cn.model.Customer;
import com.cn.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by home on 2017/7/7.
 */
@Service("customerService")
public class CustomerService implements ICustomerService {


    @Autowired
    private ICustomerDao customerDao;
    @Autowired
    private IAppLoginDao appLoginDao;



    @Override
    public List<Customer> getCustomerPageByEntity(Customer customer){
        return customerDao.pageList(customer);
    }

    @Override
    public List<Customer> getAllValidCustomer(String type) {
        return customerDao.allValidList(type);
    }

    @Override
    public Customer getCustomerByEntity(Customer customer) {
        return customerDao.find(customer);
    }
    @Override
    public void addCustomer(Customer customer) {
        customerDao.insert(customer);
    }

    @Override
    public void modifyCustomer(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class},timeout=10,isolation= Isolation.SERIALIZABLE)

    public void  addApploginCustomer( AppLogin appLogin,AppLogin lastAppLogin)throws Exception{
        if( lastAppLogin!=null) {
            appLoginDao.update(lastAppLogin);
        }
        appLoginDao.insert(appLogin);
    }

    @Override
    public AppLogin getLastApploginByEntity(AppLogin lastAppLogin) {
      return  appLoginDao.find(lastAppLogin);
    }

    public ICustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(ICustomerDao customerDao) {
        this.customerDao = customerDao;
    }



}
