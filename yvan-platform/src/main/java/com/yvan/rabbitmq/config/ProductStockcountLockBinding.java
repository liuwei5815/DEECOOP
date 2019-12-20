package com.yvan.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * RabbitMq 交换机和消息队列绑定
 * 交换机:   PRODUCT_STOCKCOUNT_LOCK_EXCHANGE
 * 消息队列: PRODUCT_STOCKCOUNT_LOCK_QUEUE
 */
public class ProductStockcountLockBinding {
    @Autowired
    private ConnectionFactory connectionFactory;

    /**
     * 获取消息队列 PRODUCT_STOCKCOUNT_LOCK_QUEUE
     * @return
     */
    @Bean
    public Queue firstQueue() {
        //durable="true" 持久化 rabbitmq重启的时候不需要创建新的队列
        return new Queue(RabbitMqConfig.PRODUCT_STOCKCOUNT_LOCK_QUEUE,true);

        //本地测试环境
        //return new Queue(RabbitMqConfig.QUEUE_TEST,true);
    }

    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(RabbitMqConfig.PRODUCT_STOCKCOUNT_LOCK_EXCHANGE,
                "x-delayed-message",
                true,
                false,
                args);
    }

    //本地测试环境
//    @Bean
//    public CustomExchange delayExchange() {
//        Map<String, Object> args = new HashMap<>();
//        args.put("x-delayed-type", "direct");
//        return new CustomExchange(RabbitMqConfig.EXCHANGE_TEST,
//                "x-delayed-message",
//                true,
//                false,
//                args);
//    }

    /**
     将消息队列1和交换机进行绑定
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(firstQueue()).to(delayExchange()).with(RabbitMqConfig.PRODUCT_STOCKCOUNT_LOCK_QUEUE).noargs();

        //本地测试环境
        //return BindingBuilder.bind(firstQueue()).to(delayExchange()).with(RabbitMqConfig.QUEUE_TEST).noargs();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        return template;
    }
}
