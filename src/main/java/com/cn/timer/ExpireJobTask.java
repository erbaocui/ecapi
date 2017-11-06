package com.cn.timer;


import com.cn.util.DateUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created by home on 2017/8/4.
 */
public class ExpireJobTask {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired


        public void doBiz() throws Exception{

           /*  System.out.println("**********************timerrun************************");
            OrderEx order=new OrderEx();
            String endTime=DateUtil.getCurrentDate("yyyy-MM-dd")+" 23:59";
            order.setEndTime(endTime);
          order.setStatus(String.valueOf(OrderStatus.FINISH.getIndex()));
            List<OrderEx> list=orderService.getOrderPageByEntity(order);
            for(OrderEx orderEx:list){
                OrderEx o=new OrderEx();
                o.setId(orderEx.getId());
                o.setStatus(String.valueOf(OrderStatus.TERMINAL.getIndex()));
                User user=new User();
                user.setId("0");
                orderService.modifyOrder(o,user);
            }*/
        }

}

