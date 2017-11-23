package com.cn.service.impl;

import com.cn.dao.ICustomerDao;
import com.cn.model.Customer;
import com.cn.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("customerService")
public class CustomerService implements ICustomerService {


    @Autowired
    private ICustomerDao customerDao;



    @Override
    public List<Customer> getCustomerPageByEntity(Customer customer){
        return customerDao.pageList(customer);
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



    public ICustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(ICustomerDao customerDao) {
        this.customerDao = customerDao;
    }

}
