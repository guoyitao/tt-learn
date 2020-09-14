package netty.inoutbounthandlertest.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * description:
 * date: 2020/9/11 17:57
 * created by: guo
 */
public class MyClienta {
    private final String host;
    private final int port;

    public MyClienta(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(group)
                     .channel(NioSocketChannel.class)
                     .handler(new MyChanneClientlInitializer());
            ChannelFuture future = bootstrap.connect(host,port)
                                            .sync();
            Channel channel = future.channel();
            System.out.println("-----"+channel.localAddress()+"---------");
//            Scanner scanner = new Scanner(System.in);
//            while (scanner.hasNextLine()){
//                String s = scanner.nextLine();
//                channel.writeAndFlush(s + "\r\n");
//            }
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        new MyClienta("127.0.0.1",8888).run();
    }
}
