package com.chat.client;

import com.chat.client.view.Index;
import com.chat.domain.ChatData;
import com.chat.domain.Message;
import com.chat.domain.MessageCallback;
import com.chat.domain.MessageType;
import com.chat.util.JsonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Client {
    private static PrintWriter printWriter = null;

    private static BufferedReader reader = null;

    private static Map<String,MessageCallback> callbacks = new ConcurrentHashMap<>();

    private static Socket socket = null;

    public static void main(String[] args) {
        try {
            clientCollection();
            //异步接受消息
            initAsynAcceptMessage();

            Index.getInstance().inputChoice();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initAsynAcceptMessage() {
        new Thread(() -> {
            while (true){
                try {
                    //换行符为读取数据的结束标记
                    String s = reader.readLine();
                    if (s == null || "".equals(s)){
                        continue;
                    }
                    //解析成消息对象
                    Message msgFromServer = JsonUtil.readToObject(s, Message.class);
                    //找出用什么回调
                    MessageCallback messageCallback = callbacks.get(msgFromServer.getMessageType().name());
                    if (messageCallback != null){
                        messageCallback.callback(msgFromServer);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
    }

    //客户端与服务器建立连接
    public static void clientCollection() throws IOException {
        //创建客户端Socket对象并指定服务器和端口
        socket = new Socket("localhost", 2000);

        //接受服务端消息
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //发消息给服务端
        printWriter = new PrintWriter(socket.getOutputStream());
    }

    /**
     * @param message 消息内容
     */
    public static void send(Message message, MessageCallback callback) throws IOException {
        if (callback != null){
            callbacks.put(message.getMessageType().name(),callback);
        }

        if (printWriter != null && reader != null) {
            printWriter.println(JsonUtil.writeObjectAsString(message));
            printWriter.flush();
        }
    }

    public static void addCallback(MessageType messageType, MessageCallback callback){
        callbacks.putIfAbsent(messageType.name(),callback);
    }

    public static void  initChat(){
        addCallback(MessageType.PrivateChatShowMsg, new MessageCallback() {
            @Override
            public void callback(Message message) {
                if (message.getState()){
                    ChatData chatData = JsonUtil.readToObject(message.getData(), ChatData.class);
                    System.out.printf("%s 对你说： %s\n",chatData.getFrom(),chatData.getData());
                }

            }
        });
        addCallback(MessageType.GroupChatShowMsg, new MessageCallback() {
            @Override
            public void callback(Message message) {
                ChatData chatData = JsonUtil.readToObject(message.getData(), ChatData.class);
                System.out.printf("聊天室消息 %s 说：%s\n",chatData.getFrom(),chatData.getData());
            }
        });

        addCallback(MessageType.Exit, new MessageCallback() {
            @Override
            public void callback(Message message) {
                System.out.println("账号在其他地方登录,您已被迫下线");
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.exit(0);
            }
        });
    }

    public static void closeSocket(){
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void stopChat() {
        callbacks.remove(MessageType.PrivateChat.name());
        callbacks.remove(MessageType.GroupChatShowMsg.name());
    }
}