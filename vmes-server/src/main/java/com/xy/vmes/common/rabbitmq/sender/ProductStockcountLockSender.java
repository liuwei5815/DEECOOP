package com.xy.vmes.common.util.rabbitmq.sender;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.yvan.rabbitmq.config.RabbitMqConfig;

/**
 * 生产者 消息发送
 * @author 陈刚
 * @date 2019-06-13
 */
@Component
public class ProductStockcountLockSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     * @param message    消息内容
     * @param timeStamp  消息队列中的时间(毫秒单位)
     */
    public void sendMsg(String message, Integer timeStamp) {
        System.out.println("(Product)发送消息:" + message + " timeStamp:" + timeStamp.toString());

        rabbitTemplate.convertAndSend(RabbitMqConfig.PRODUCT_STOCKCOUNT_LOCK_EXCHANGE,
                RabbitMqConfig.PRODUCT_STOCKCOUNT_LOCK_QUEUE,
                message,
                new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        message.getMessageProperties().setHeader("x-delay", timeStamp);
                        return message;
                    }
                });

//        //本地测试环境
//        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_TEST,
//                RabbitMqConfig.QUEUE_TEST,
//                message,
//                new MessagePostProcessor() {
//                    @Override
//                    public Message postProcessMessage(Message message) throws AmqpException {
//                        message.getMessageProperties().setHeader("x-delay", timeStamp);
//                        return message;
//                    }
//                });
    }

}
