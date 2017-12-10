package com.cn.dao.impl;

import com.cn.dao.IMessageDao;
import com.cn.model.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("messageDao")
public class MessageDaoImpl extends BaseDaoImpl implements IMessageDao {

    @Override
    public List<Message> pageList(Message message) {
        return (List<Message>)list("com.cn.dao.MessageMapper.selectPageByEntity", message);

    }


    @Override
    public void insert(Message message) {
        addObject("com.cn.dao.MessageMapper.insert", message);
    }

    @Override
    public void update(Message message) {
        updateObject("com.cn.dao.MessageMapper.update", message);
    }

    @Override
    public Message find(Message message) {
        return (Message)findObject("com.cn.dao.MessageMapper.selectOneByEntity", message);
    }
    @Override
    public void delete(Message message) {
        deleteObject("com.cn.dao.MessageMapper.deleteByPrimaryKey", message.getId());
    }

    @Override
    public int count(Message message) {
        return (Integer)findObject("com.cn.dao.MessageMapper.selectCount", message);
    }
}
