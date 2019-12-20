package com.yvan.socketio;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by 46368 on 2018/12/4.
 */
@Configuration
public class SocketIOConfig {


    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();

        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){   //在本地window环境测试时用localhost
            System.out.println("this is  windows");
            config.setHostname("192.168.0.108");
        } else {
            config.setHostname("116.196.105.79");   //部署到你的远程服务器正式发布环境时用服务器公网ip
        }
        config.setHostname("0.0.0.0");
        // 协议升级超时时间（毫秒），默认10000。HTTP握手升级为ws协议超时时间
        config.setUpgradeTimeout(10000);
        // Ping消息间隔（毫秒），默认25000。客户端向服务器发送一条心跳消息间隔
        config.setPingInterval(60000);
        // Ping消息超时时间（毫秒），默认60000，这个时间间隔内没有接收到心跳消息就会发送超时事件
        config.setPingTimeout(180000);
        // 设置监听端口
        config.setPort(8090);

		/*config.setAuthorizationListener(new AuthorizationListener() {//类似过滤器
			@Override
			public boolean isAuthorized(HandshakeData data) {
				//http://localhost:8081?username=test&password=test
				//例如果使用上面的链接进行connect，可以使用如下代码获取用户密码信息，本文不做身份验证
				// String username = data.getSingleUrlParam("username");
				// String password = data.getSingleUrlParam("password");
				return true;
			}
		});*/


        final SocketIOServer server = new SocketIOServer(config);




        /*
         * 添加连接监听事件，监听是否与客户端连接到服务器
         */
        server.addConnectListener(new ConnectListener() {

            @Override
            public void onConnect(SocketIOClient client) {
                // 判断是否有客户端连接
                if (client != null) {
                    System.out.println("连接成功。clientId=" + client.getSessionId());
//                    client.joinRoom(client.getHandshakeData().getSingleUrlParam("appid"));
                } else {
                    System.out.println("并没有人连接上。。。");
                }
            }


        });

                /*
         * 添加连接监听事件，监听是否与客户端连接到服务器
         */
        server.addDisconnectListener(new DisconnectListener() {

            @Override
            public void onDisconnect(SocketIOClient client) {
                System.out.println("连接失败。。。");
            }
        });


                /*
         * 添加监听事件，监听客户端的事件
         * 1.第一个参数eventName需要与客户端的事件要一致
         * 2.第二个参数eventClase是传输的数据类型
         * 3.第三个参数listener是用于接收客户端传的数据，数据类型需要与eventClass一致
         */
        server.addEventListener("login", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient client, String data, AckRequest ackRequest) {
                System.out.println("接收到客户端login消息：body = " + data);
                // check is ack requested by client, but it's not required check
                if (ackRequest.isAckRequested()) {
                    // send ack response with data to client
                    ackRequest.sendAckData("已成功收到客户端登录请求", "yeah");
                }
                // 向客户端发送消息
                List<String> list = new ArrayList<>();
                list.add("登录成功，sessionId=" + client.getSessionId());
                server.getClient(client.getSessionId()).sendEvent("login", list);
                // 第一个参数必须与eventName一致，第二个参数data必须与eventClass一致
//                String room = client.getHandshakeData().getSingleUrlParam("appid");
//                server.getRoomOperations(room).sendEvent("login", list.toString());
            }
        });



        server.start();
        System.out.println("socket.io启动成功！");

        return server;
    }

//    @Bean
//    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
//        return new SpringAnnotationScanner(socketServer);
//    }

}
