package com.cn.service;

import com.cn.model.Customer;
import com.cn.model.Message;

import java.util.List;

public interface IMessageService {
    //用户
    public List<Message> getMessagePageByEntity(Message message);
    public Message getMessageByEntity(Message message);
    public void addMessage(Message message);
    public void modifyMessage(Message message);
    public void deleteMessage(Message message);
    public void modifyCustomerMessageHavenRead(Customer customer);
    public int getMessageUnreadCount(Customer customer);
}
