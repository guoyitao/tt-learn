package nio.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 群聊客户端
 * @Author: guo
 * @CreateDate: 2019/3/19
 * @UpdateUser:
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        //创建一个SocketChannel
        SocketChannel socketChannel = SocketChannel.open();
        //非阻塞
        socketChannel.configureBlocking(false);
        //创建选择器
        Selector selector = Selector.open();
        //注册 设置事件为 链接
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        //链接到服务器
        socketChannel.connect(new InetSocketAddress("127.0.0.1",NIOServer.serverPort));

        listen(selector);
        return;

    }

    private static void listen(Selector selector) throws IOException {
        while (true){
            //等待事件
            selector.select();
            //获取事件token
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                //读取事件
                SelectionKey selectionKey = iterator.next();
                //处理事件 判断是不是OP_CONNECT事件
                if (selectionKey.isConnectable()) {
                    handlConnectable(selector, selectionKey);
                } else if (selectionKey.isReadable()) {
                    handleReadable(selectionKey);

                }
            }
            //之前讲过
            selectionKeys.clear();
        }
    }

    private static void handleReadable(SelectionKey selectionKey) throws IOException {
        //收数据
        SocketChannel client = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        int read = client.read(byteBuffer);
        if (read > 0) {

            String receiveData = new String(byteBuffer.array(),0,read);
            System.out.println("收到消息: " + receiveData);
        }
    }

    private static void handlConnectable(Selector selector, SelectionKey selectionKey) throws IOException {
        //获得SocketChannel
        SocketChannel client = (SocketChannel) selectionKey.channel();
//                    建立连接
        if (client.isConnectionPending()) {
            //完成链接
            client.finishConnect();

            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            writeBuffer.put((LocalDateTime.now() + "连接成功").getBytes());
            writeBuffer.flip();
            //发送链接成功信息
            client.write(writeBuffer);
            //创建一个单个线程的线程池
            ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
            executorService.submit(() -> {
                while (true) {
                    try {
                        //这里之前讲过
                        writeBuffer.clear();
                        //标准输入，从键盘输入
                        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                        String line = reader.readLine();

                        writeBuffer.put(line.getBytes());
                        writeBuffer.flip();
                        //从客户端向服务端发送数据
                        client.write(writeBuffer);
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
            });

        }

        client.register(selector, SelectionKey.OP_READ);
    }
}
