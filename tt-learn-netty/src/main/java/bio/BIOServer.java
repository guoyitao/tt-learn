package bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {

    //线程安全map   <用户sessionId,用户session>
    public static ConcurrentHashMap<String,Session> sessonMap = new ConcurrentHashMap<>();

    //线程安全map   <群聊号<用户sessionId,用户session>>
    public static ConcurrentHashMap<String, ConcurrentHashMap<String,Session>> roomMap = new ConcurrentHashMap<>();

    public static final String commonRoom = "common"; //群聊房间

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动了！！！");

        while (true){
            System.out.println("等待连接");
            Socket accept = serverSocket.accept();
            System.out.println("连到一个客户端");
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    hander(accept);
                }
            });
        }

    }

    private static void hander(Socket accept) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            PrintWriter pw = new PrintWriter(accept.getOutputStream());
            while (true){
                try {
                    String data = br.readLine();
                    System.out.println("接受到消息：" + data);
                    pw.println("收到client");
                    pw.flush();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                accept.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
