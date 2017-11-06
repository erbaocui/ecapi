package com.cn.controller.token;

import com.cn.model.Customer;
import com.cn.model.User;
import com.cn.util.SerializeUtil;

import java.io.Serializable;

/**
 * Created by home on 2017/10/25.
 */
public class TokenModel implements Serializable {

    private Customer customer;
    private String token ;

    public TokenModel(Customer customer, String token) {
        this. customer =  customer;
        this.token = token;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
