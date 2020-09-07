package bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
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
            InputStream inputStream = accept.getInputStream();
            byte[] buffer = new byte[1024];
            while (true){
                int read = inputStream.read(buffer);
                if (read != -1){
                    System.out.printf(new String(buffer,0,read));
                }else {
                    break;
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
