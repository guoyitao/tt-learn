package com.chat.domain;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @description:用户会话类,试用用户名作为每个客户端的唯一标识
 * @author: Luck_chen
 * @date: 2022/11/8 21:41
 * @Version 1.0.0.0
 */
public class Session {
    private String username;
    private Socket socket;

    public Session(String username, Socket socket) {
        this.username = username;
        this.socket = socket;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void send(String message) throws IOException {
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        pw.println(message);
        pw.flush();
    }
}
