package bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientMain {
    public static void main(String args[])throws Exception{
        Socket socket = new Socket("127.0.0.1", 6666);
        System.out.println("小一连接成功");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        new Thread(new Runnable() {

            @Override
            public void run() {
                    while (true){
                        try {
                            System.out.println(serverReader.readLine());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
            }
        }).start();
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        while(true){
            pw.println("小一说："+br.readLine());
            pw.flush();
        }
    }


}
