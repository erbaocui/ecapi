package com.cn.service.impl;

import com.cn.dao.ICustomerMessageDao;
import com.cn.dao.IMessageDao;
import com.cn.model.Customer;
import com.cn.model.CustomerMessage;
import com.cn.model.Message;
import com.cn.service.ICustomerMessageService;
import com.cn.service.IMessageService;
import com.cn.util.IdGenerator;
import com.cn.vo.MessageEx;
import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("messageService")
public class MessageService implements IMessageService {


    @Autowired
    private IMessageDao messageDao;
    @Autowired
    private ICustomerMessageDao customerMessageDao;
    public IMessageDao getMessageDao() {
        return messageDao;
    }

    public void setMessageDao(IMessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public ICustomerMessageDao getCustomerMessageDao() {
        return customerMessageDao;
    }

    public void setCustomerMessageDao(ICustomerMessageDao customerMessageDao) {
        this.customerMessageDao = customerMessageDao;
    }

    @Override
    public List<Message> getMessagePageByEntity(Message message){
        return messageDao.pageList(message);
    }


    @Override
    public Message getMessageByEntity(Message message) {
        return messageDao.find(message);
    }
    @Override
    public void addMessage(Message message) {
        messageDao.insert(message);
    }

    @Override
     public void modifyMessage(Message message) {
        messageDao.update(message);
    }
    @Override
    public void  deleteMessage(Message message) {
        messageDao.delete(message);
    }
    @Override
    public void modifyCustomerMessageHavenRead(Customer customer) {
        MessageEx message =new MessageEx();
        message.setStatus("0");
        List<Message> list=messageDao.pageList(message);
        for(Message msg:list){
            CustomerMessage   customerMessage=new   CustomerMessage();
            customerMessage.setCustomerId(customer.getId());
            customerMessage.setMessageId(msg.getId());
            CustomerMessage   cm=customerMessageDao.find(customerMessage);
            if(cm==null){
                customerMessage.setId(IdGenerator.getId());
                customerMessage.setMessageId(msg.getId());
                customerMessageDao.insert(customerMessage);
            }

        }
    }

    @Override
    public int getMessageUnreadCount(Customer customer) {
        MessageEx message =new MessageEx();
        message.setStatus("0");
        int messageCount=messageDao.count(message);
        CustomerMessage   customerMessage=new   CustomerMessage();
        customerMessage.setCustomerId(customer.getId());
        int havenReadCount=customerMessageDao.count(customerMessage);
        return messageCount-havenReadCount;
    }
}
