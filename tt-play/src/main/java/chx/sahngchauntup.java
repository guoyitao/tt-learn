package chx;

import java.io.*;
import java.net.Socket;

public class sahngchauntup {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",8888);
            FileInputStream fis = new FileInputStream("");
            BufferedOutputStream out = new  BufferedOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fis.read(buf)) != -1){
                out.write(buf,0,len);
            }
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
