package com.yvan.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.jms.Queue;

/**
 * Created by 46368 on 2018/12/3.
 */
@Configuration
public class ActiveMQConfig {

    /**
     * 销售-订单管理-订单明细-订单明细货品锁定库存-消息队列定义
     *
     * @return
     */
    @Bean
    public Queue orderDetailByProductLockcountQueue() {
        //消息队列-真实环境
        return new ActiveMQQueue("sale.orderDetail.ProductLockcount.queue");

        //本地消息队列测试
        //return new ActiveMQQueue("sale.orderDetail.ProductLockcount.queue_test");
    }


}
