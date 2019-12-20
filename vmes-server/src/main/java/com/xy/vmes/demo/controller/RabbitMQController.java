package com.xy.vmes.demo.controller;

import com.xy.vmes.common.util.rabbitmq.sender.ProductStockcountLockSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {

    @Autowired
    public ProductStockcountLockSender firstSender;
    @GetMapping("/test/sendRabbitTest")
    public void sendTest() {
        //发送第一个消息
        Integer firstTimeStamp = Integer.valueOf(10 * 1000);
        firstSender.sendMsg("11111", firstTimeStamp);

        //发送第二个消息
        Integer secondTimeStamp = Integer.valueOf(10 * 1000);
        firstSender.sendMsg("22222", secondTimeStamp);

    }
}
