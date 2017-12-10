package com.cn.dao.impl;

import com.cn.dao.ICustomerMessageDao;
import com.cn.model.CustomerMessage;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("customerMessageDao")
public class CustomerMessageDaoImpl extends BaseDaoImpl implements ICustomerMessageDao {

    @Override
    public List<CustomerMessage> pageList(CustomerMessage customerMessage) {
        return (List<CustomerMessage>)list("com.cn.dao.CustomerMessageMapper.selectPageByEntity", customerMessage);

    }


    @Override
    public void insert(CustomerMessage customerMessage) {
        addObject("com.cn.dao.CustomerMessageMapper.insert", customerMessage);
    }

    @Override
    public void update(CustomerMessage customerMessage) {
        updateObject("com.cn.dao.CustomerMessageMapper.update", customerMessage);
    }

    @Override
    public CustomerMessage find(CustomerMessage customerMessage) {
        return (CustomerMessage)findObject("com.cn.dao.CustomerMessageMapper.selectOneByEntity", customerMessage);
    }
    @Override
    public void delete(CustomerMessage customerMessage) {
        deleteObject("com.cn.dao.CustomerMessageMapper.deleteByPrimaryKey", customerMessage.getId());
    }

    @Override
    public int count(CustomerMessage customerMessage) {
        return (Integer)findObject("com.cn.dao.CustomerMessageMapper.selectCount", customerMessage);
    }
}
