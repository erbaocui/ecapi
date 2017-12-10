package com.cn.dao;

import com.cn.model.Message;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface IMessageDao {


    public List<Message> pageList(Message message);
    public int count(Message message);
    public void insert(Message message);
    public void update(Message message);
    public Message find(Message message);
    public void delete(Message message);


}