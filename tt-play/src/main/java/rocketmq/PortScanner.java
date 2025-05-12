package rocketmq;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PortScanner {

    private static final String HOST = "http://10.142.255.4";
    private static final int THREAD_POOL_SIZE = 2000; // 可以根据需要调整线程池大小

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        for (int port = 1; port <= 65535; port++) {
            final int currentPort = port;
            executorService.submit(() -> checkPort(currentPort));
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void checkPort(int port) {
        try {
            URL url = new URL(HOST + ":" + port);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(500); // 设置连接超时时间
            connection.setReadTimeout(1000); // 设置读取超时时间

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                if (content.toString().contains("<html")) {
                    System.out.println("Port " + port + " returned HTML content.");
                }
            }
        } catch (Exception e) {
            // 忽略连接失败等异常
        }
    }
}
