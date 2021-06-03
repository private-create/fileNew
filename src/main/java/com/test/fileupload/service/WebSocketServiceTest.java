package com.test.fileupload.service;

import com.test.fileupload.DTO.DriverImageDTO;
import com.test.fileupload.entity.DriverImage;
import com.test.fileupload.service.impl.OSSServiceImpl;
import com.test.fileupload.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/websocket/server/{mac}")
@Component
@Slf4j
public class WebSocketServiceTest {

    /**
     * 存放所有在线的客户端
     */
    private static Map<String, Session> clients = new ConcurrentHashMap<>();


    private static ApplicationContext applicationContext;
    

    public static void setApplicationContext(ApplicationContext applicationContext){
        WebSocketServiceTest.applicationContext=applicationContext;
    }



    @OnOpen
    public void onOpen(Session session, @PathParam("mac") String mac) {
        log.info("有新的客户端连接了: {}", mac);
        //将新用户存入在线的组
        clients.put(mac, session);
    }

    /**
     * 客户端关闭
     * @param session session
     */
    @OnClose
    public void onClose(Session session) {
        log.info("有用户断开了, id为:{}", session.getId());
        //将掉线的用户移除在线的组里
        clients.remove(session.getId());
    }

    /**
     * 发生错误
     * @param throwable e
     */
    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * 收到客户端发来消息
     * @param message  消息对象
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("服务端收到客户端发来的消息: {}", message);
        if(message.contains("flag")) {
            DriverImageDTO driverImageDTO = JsonUtil.JsonStringToObject(message, DriverImageDTO.class);
            OSSService ossService = applicationContext.getBean(OSSService.class);
            ossService.oSSUpload(driverImageDTO);
        }
//        this.sendAll(message);
    }

    /**
     * 群发消息
     * @param message 消息内容
     */
    private void sendAll(String message) {
        for (Map.Entry<String, Session> sessionEntry : clients.entrySet()) {
            sessionEntry.getValue().getAsyncRemote().sendText(message);
        }
    }

    /**
     * 发送消息
     * @param mac
     * @param driverImage
     */
    private void sendTo(String mac, DriverImage driverImage) {
        Session s = clients.get(mac);
        if (s != null) {
            try {
//                s.getBasicRemote().sendText(driverImage);
                s.getBasicRemote().sendObject(driverImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
