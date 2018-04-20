package com.share.controller;

import org.apache.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by weixin on 17-10-1.
 */
@ServerEndpoint("/websocket")
public class WebSocket {
    private static final Logger logger=Logger.getLogger(WebSocket.class);
    private static int onlineCount=0;
    private static CopyOnWriteArrayList<WebSocket> webSocketSet=new CopyOnWriteArrayList<WebSocket>();
    private Session session;

    @OnOpen
    public void onOpen(Session session){
        this.session=session;
        webSocketSet.add(this);
        addOnlineCount();
        System.out.println("有新连接的人，当前在线人数为："+getOnlineCount());
        logger.info(webSocketSet.toString()+"0000000000000000");
    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        subOnlineCount();
        System.out.println("有一个连接关闭。当前在线人数为："+getOnlineCount());
    }
    @OnMessage
    public void onMessage(String message,Session session){
        System.out.println("来自客户端"+session.getId()+"的消息："+message);

        for(WebSocket item:webSocketSet){
            try {
                item.sendMessage("我说："+message,session);
                Scanner scanner=new Scanner(System.in);
                String str=scanner.nextLine();
                item.sendMessage("服务端："+str,session);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }

    }

    @OnError
    public void onError(Session session,Throwable error){
        System.out.println(session+"号客户端发生错误");
        error.printStackTrace();
    }

    public void sendMessage(String message,Session session) throws IOException {
        session.getBasicRemote().sendText(message);
    }


    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }
}
