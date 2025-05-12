package yaxintool;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

class HttpTest {

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://10.131.156.122:7053/apis/v1/dhsmz/pip000000207");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("POST");

        httpConn.setRequestProperty("x-sg-timestamp", "20240701150600");
        httpConn.setRequestProperty("x-sg-message-id", "550e8400e29b41d4a71644665544007");
        httpConn.setRequestProperty("x-sg-test", "1");
        httpConn.setRequestProperty("x-sg-api-version", "1.0.0");
        httpConn.setRequestProperty("x-sg-api-code", "pip000000207");
        httpConn.setRequestProperty("x-sg-route-type", "03");
        httpConn.setRequestProperty("x_sg_route_value", "boss2900");
        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        httpConn.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
        writer.write("{\"ReqId\":\"\",\"oprNumb\":\"57e367fbb1b141c8bbb9ca95f6e41864\",\"provinceId\":\"290\",\"materialCode\":\"910200000005671\",\"partnerName\":\"\u4E00\u7EA7\u8425\u9500\u7CFB\u7EDF\u7BA1\u7406\u673A\u6784\",\"terminalName\":\"jjxx1205\",\"productName\":\"\u4E0B\u53D1\u5206\u7701\u7EC8\u7AEF\u6570\u636E\",\"tactuiList\":[{\"tactuiCode\":\"35901000\"}],\"contractOperation\":\"1\",\"validDate\":\"20241223\"}");
        writer.flush();
        writer.close();
        httpConn.getOutputStream().close();

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        System.out.println(response);
    }
}
