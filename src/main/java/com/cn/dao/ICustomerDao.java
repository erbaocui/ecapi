package com.cn.dao;

import com.cn.model.Customer;
import com.cn.model.User;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface ICustomerDao {


    public List<Customer> pageList(Customer customer);
    public List<Customer> allValidList(String type);
    public void insert(Customer customer);
    public void update(Customer customer);
    public Customer find(Customer customer);


}