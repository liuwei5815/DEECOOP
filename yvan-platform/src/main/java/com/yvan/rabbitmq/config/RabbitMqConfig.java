package com.yvan.rabbitmq.config;

public class RabbitMqConfig {

    /** 交换机名称*/
    //RabbitMq交换机名字:PRODUCT_STOCKCOUNT_LOCK_EXCHANGE 对应业务:货品锁定库存数量
    public static final String PRODUCT_STOCKCOUNT_LOCK_EXCHANGE = "product_stockcount_lock_exchange";
    //public static final String EXCHANGE_A = "my-mq-exchange_A";
    //public static final String EXCHANGE_B = "my-mq-exchange_B";
    //public static final String EXCHANGE_C = "my-mq-exchange_C";

    //RabbitMq消息队列名称:PRODUCT_STOCKCOUNT_LOCK_QUEUE 对应业务:货品锁定库存数量
    public static final String PRODUCT_STOCKCOUNT_LOCK_QUEUE = "product_stockcount_lock_queue";
    //public static final String QUEUE_A = "QUEUE_A";
    //public static final String QUEUE_B = "QUEUE_B";
    //public static final String QUEUE_C = "QUEUE_C";

    //public static final String IMMEDIATE_ROUTING_KEY = "spring-boot-routingKey_A";
    //public static final String DELAY_ROUTING_KEY = "spring-boot-routingKey_B";
    //public static final String ROUTINGKEY_C = "spring-boot-routingKey_C";

    //////////////////////////////////////////////////////////////////////////
    //本地测试环境 本地验证接收消息时 必须使用测试环境(交换机名称, 消息队列名称)-否则本地接收不到RabbitMq消息
    //public static final String EXCHANGE_TEST = "exchange_test";
    //public static final String QUEUE_TEST = "queue_test";

//    @Bean public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(this.host, this.port);
//        cachingConnectionFactory.setUsername(this.userName);
//        cachingConnectionFactory.setPassword(this.password);
//        cachingConnectionFactory.setVirtualHost("/");
//        cachingConnectionFactory.setPublisherConfirms(true);
//        return cachingConnectionFactory;
//    }



}
