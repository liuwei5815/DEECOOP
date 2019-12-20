package com.xy.vmes.common.util.activemq;


import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.jms.ObjectMessage;
import javax.jms.Queue;

/**
 * Created by 46368 on 2018/11/30.
 */


@Component
@EnableScheduling
public class Producer {

    @Autowired
    private JmsTemplate jmsTemplate;

//    @Autowired
//    private Queue queue;


    public void sendMsg(String msg,Long deadTime) {
//        jmsTemplate.send(queue, session -> {
//            ObjectMessage objectMessage = session.createObjectMessage(msg);
//            objectMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,deadTime);
//            return objectMessage;
//        });
    }


//    @Scheduled(fixedDelay = 20000)    // 每2s执行1次
//    public void send() {
//        System.out.println("sample.queue队列发送的回复报文为:");
//        jmsTemplate.convertAndSend(queue, "hello,activeMQ");
//    }

}
