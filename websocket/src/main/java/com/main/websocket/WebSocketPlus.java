//package com.main.websocket;
//
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.http.server.ServletServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.server.HandshakeInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Map;
//
///**
// * websocket加强版
// * @author Mr.LB
// * @date 2019-09-20
// */
//
////@Component
//public class WebSocketPlus implements HandshakeInterceptor {
//
//    @Override
//    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Map<String, Object> map) {
//        if (request instanceof ServletServerHttpRequest) {
//            HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
//            // 从session中获取到当前登录的用户信息. 作为socket的账号信息. session的的WEBSOCKET_USERNAME信息,在用户打开页面的时候设置.
//            String userName = (String) servletRequest.getSession().getAttribute("WEBSOCKET_USERNAME");
//            map.put("WEBSOCKET_USERNAME", userName);
//        }
//        return true;
//    }
//
//    @Override
//    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Exception e) {
//
//    }
//}
