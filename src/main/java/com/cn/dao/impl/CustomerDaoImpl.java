package com.cn.dao.impl;

import com.cn.dao.ICustomerDao;
import com.cn.dao.ICustomerDao;
import com.cn.model.Customer;
import com.cn.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl implements ICustomerDao {

    @Override
    public List<Customer> pageList(Customer customer) {
        return (List<Customer>)list("com.cn.dao.CustomerMapper.selectPageByEntity", customer);

    }

    @Override
    public List<Customer> allValidList(String type) {
        return (List<Customer>)list("com.cn.dao.CustomerMapper.selectAllValid", type);
    }

    @Override
    public void insert(Customer customer) {
        addObject("com.cn.dao.CustomerMapper.insert", customer);
    }

    @Override
    public void update(Customer customer) {
        updateObject("com.cn.dao.CustomerMapper.updateByPrimaryKey", customer);
    }

    @Override
    public Customer find(Customer customer) {
        return (Customer)findObject("com.cn.dao.CustomerMapper.selectOneByEntity", customer);
    }
}
