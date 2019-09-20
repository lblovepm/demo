package com.main.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 1、websocket可以通过http://www.websocket-test.com/网站进行国内测试
 * 2、启动WebSocketSpringBootApplication，在测试网站输入ws://127.0.0.1:8082/websocket/simple就可以连接了
 */
@ServerEndpoint(value = "/websocket/simple")
@Component
public class WebSocketSimple {

    //在线人数,默认为0,AtomicLong可以保证线程安全
    private static AtomicLong onlineNumber = new AtomicLong(0);

    //ConcurrentHashMap,用来存放每个客户端对应的Session对象。
    private static ConcurrentHashMap<String,Session> sessionMap = new ConcurrentHashMap<>();

    /**
     * 客户端和服务器成功建立连接
     * @param session
     * @throws IOException
     */
    @OnOpen
    public void onOpen(Session session,EndpointConfig conf) throws IOException {

        //session存入
        sessionMap.put(session.getId(),session);

        //在线人数+1
        onlineNumber.incrementAndGet();

//        session.getBasicRemote().sendText("客户端(sessionId="+session.getId()+")上线！当前在线人数为:" + onlineNumber.get());

        System.out.println("客户端(sessionId="+session.getId()+")上线！当前在线人数为:" + onlineNumber.get());
    }

    /**
     * 客户端连接关闭
     * @param session
     */
    @OnClose
    public void onClose(Session session,CloseReason reason) {

        //session移除
        sessionMap.remove(session.getId());

        //在线人数-1
        onlineNumber.decrementAndGet();

        System.out.println("客户端(sessionId="+session.getId()+")关闭！关闭原因为："+reason.getReasonPhrase()+"!当前在线人数为:" + onlineNumber.get());
    }

    /**
     * 收到客户端发送的消息
     * @param session
     * @param message
     * @throws IOException
     */
    @OnMessage
    public void onMessage(Session session,String message) throws IOException {

        JSONObject messageJson = JSON.parseObject(message);
        System.out.println("userId----->"+messageJson.getInteger("userId"));

        System.out.println("来自客户端(sessionId="+session.getId()+")的消息:" + messageJson.getString("message"));

        Set<String> sessionIdSet = sessionMap.keySet();
        for (String sessionId : sessionIdSet) {
            if(!sessionId.equals(session.getId())){
                sessionMap.get(sessionId).getBasicRemote().sendText(message);
            }
        }
    }

    /**
     * 发生错误
     * @param session
     * @param error
     */
     @OnError
     public void onError(Session session, Throwable error) {
         System.out.println("客户端(sessionId="+session.getId()+")发生错误:"+error.getMessage());
     }
}
