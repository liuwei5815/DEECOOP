package com.xy.vmes.demo.controller;

import com.corundumstudio.socketio.*;
import com.xy.vmes.common.util.activemq.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ActiveMQController {


    @Autowired
    private Producer producer;
    @Autowired
    private SocketIOServer socketIOServer;

    @GetMapping("/test/sendTest")
    public void sendTest() throws JMSException {
        String msg_1 = "11111";
        System.out.println("Receiver发送的报文为:"+msg_1);
        producer.sendMsg(msg_1, Long.valueOf(10 * 1000));

        String msg_2 = "22222";
        System.out.println("Receiver发送的报文为:"+msg_2);
        producer.sendMsg(msg_2, Long.valueOf(10 * 1000));

        String msg_3 = "33333";
        System.out.println("Receiver发送的报文为:"+msg_3);
        producer.sendMsg(msg_3, Long.valueOf(10 * 1000));
    }

    @GetMapping("/test/sendSocketIO")
    public void sendSocketIO() throws JMSException {
        // 向客户端发送消息
        List<String> list = new ArrayList<>();
        list.add("ssssssssssss");
        socketIOServer.getBroadcastOperations().sendEvent("abb84155-af55-470b-9b2e-7f539f58ac06", list);

//        socketIOServer.addEventListener("login", String.class, new DataListener<String>() {
//            @Override
//            public void onData(SocketIOClient client, String data, AckRequest ackRequest) {
//                System.out.println("接收到客户端login消息：body = " + data);
//                // check is ack requested by client, but it's not required check
//                if (ackRequest.isAckRequested()) {
//                    // send ack response with data to client
//                    ackRequest.sendAckData("已成功收到客户端登录请求", "yeah");
//                }
//                // 向客户端发送消息
//                List<String> list = new ArrayList<>();
//                list.add("登录成功，sessionId=" + client.getSessionId());
//                socketIOServer.getClient(client.getSessionId()).sendEvent("login", list);
//                // 第一个参数必须与eventName一致，第二个参数data必须与eventClass一致
////                String room = client.getHandshakeData().getSingleUrlParam("appid");
////                server.getRoomOperations(room).sendEvent("login", list.toString());
//            }
//        });
    }

}
