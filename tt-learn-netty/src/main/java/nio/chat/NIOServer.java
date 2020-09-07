package nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * remove SelectKey是为了防止下一次select的时候重复处理
 * @Description: 群聊服务器
 * @Author: guo
 * @CreateDate: 2019/3/19
 * @UpdateUser:
 */
public class NIOServer {
    public static final int serverPort = 5001;
    public static final Map<String, SocketChannel> clientMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //非阻塞
        serverSocketChannel.configureBlocking(false);
        //服务器接套字
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(serverPort));
        //注册准备接收事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器端口： " + serverPort);


        listen(selector);

    }

    private static void listen(Selector selector) throws IOException {
        while (true){
            //等待事件
            selector.select();
            //获取事件token
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                //读取事件
                SelectionKey selectionKey = iterator.next();

                //处理事件
                if (selectionKey.isAcceptable() && selectionKey.isValid()){
                    handlerAcceptable(selector, iterator, selectionKey);
                }else if (selectionKey.isReadable()&& selectionKey.isValid()){
                    handlerReader(iterator, selectionKey);
                }
            }


        }
    }

    private static void handlerReader(Iterator<SelectionKey> iterator, SelectionKey selectionKey) {
        SocketChannel client = (SocketChannel) selectionKey.channel();

        //接受数据
        try {
            while (true){
                ByteBuffer readBuffer = ByteBuffer.allocateDirect(1024);
                readBuffer.clear();

                //来自哪个用户
                String fromKey = null;
                for (Map.Entry<String, SocketChannel> channelEntry : clientMap.entrySet()) {
                    if (channelEntry.getValue() == client){
                        fromKey = channelEntry.getKey();
                        break;
                    }
                }

                int read = 0;

                try {
                    read = client.read(readBuffer);
                } catch (IOException e) {
                    /*
                    * 客户端异常关闭处理 java.io.IOException: 远程主机强迫关闭了一个现有的连接。
                    * */
                    client.close();
                    selectionKey.cancel();
                    e.printStackTrace();

                    /*
                    * 必须把clientMap原来的SocketChannel剔除掉,因为原来的SocketChannel已经关闭
                    * 如果不剔除可能抛出java.nio.channels.ClosedChannelException
                    * */
                    clientMap.remove(fromKey);
                    break;
                }
                if (read == -1){
                    client.close();
                    selectionKey.cancel();
                    clientMap.remove(fromKey);
                    break;
                }else if(read == 0){
                    break;
                }

                readBuffer.flip();

                //转码取值
                Charset charset = Charset.forName("utf-8");
                String receiveData = String.valueOf(charset.decode(readBuffer).array());

                //发送
                System.out.println("服务器收到" + receiveData + "，来自:" + client);
                for (Map.Entry<String, SocketChannel> channelEntry : clientMap.entrySet()) {
                    SocketChannel value = channelEntry.getValue();
                    ByteBuffer writeBuffer = ByteBuffer.allocateDirect(1024);

                    writeBuffer.put((fromKey + receiveData).getBytes());
                    writeBuffer.flip();

                    value.write(writeBuffer);
                }
                iterator.remove();
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private static void handlerAcceptable(Selector selector, Iterator<SelectionKey> iterator, SelectionKey selectionKey) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
        //接受与此通道的套接字建立的连接, 这是用户连接！！！！！！！！！
        SocketChannel client = server.accept();
        //非阻塞
        client.configureBlocking(false);
        //连接成功准备接受读事件
        client.register(selector, SelectionKey.OP_READ);
        //保存连接用户
        clientMap.put(UUID.randomUUID().toString(),client);

        iterator.remove();
    }
}
