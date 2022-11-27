package com.chat.server;

import com.chat.domain.Message;
import com.chat.server.service.CommonService;
import com.chat.util.JsonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static final CommonService service = new CommonService();
    public static final ExecutorService executorService = Executors.newFixedThreadPool(20);
    public static void main(String[] args) {
        try {
            startServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void startServer() throws IOException {
        //创建ServerSocket对象并指定端口号（相当于开启了一个服务器）
        ServerSocket server = new ServerSocket(2000);
        System.out.println("服务器已启动，等待连接");
        //调用ServerSocket对象的accept方法等待客端户连接并获得对应Socket对象
        while (true){
            //客户端对象
            Socket socket = server.accept();
            System.out.println("有一个新的连接");
            executorService.execute(new Runnable() {
                    @Override
                public void run() {
                    handlerMessage(socket);
                }
            });
        }
    }

    /**
     * 处理请求
     */
    public static void handlerMessage(Socket socket){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            while (true){
                String data = br.readLine();
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                //解析客户端消息
                if (data != null && !"".equals(data)){
                    Message response = service.handler(JsonUtil.readToObject(data, Message.class),socket);
                    //响应给客户端
                    pw.println(JsonUtil.writeObjectAsString(response));
                    //清空缓存
                    pw.flush();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}