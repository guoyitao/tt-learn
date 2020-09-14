package netty.example1.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * description
 * @Date 2020/9/7 22:53
 * @Created by guo
 */
public class NettyClient {
    public static void main(String[] args) throws Exception{
        EventLoopGroup eventExecutors = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(eventExecutors)
                     .channel(NioSocketChannel.class)
                     .handler(new ChannelInitializer<SocketChannel>() {
                         @Override
                         protected void initChannel(SocketChannel ch) {
                             ch.pipeline().addLast(new NettyClientHandler());
                         }
                     });

            System.out.println("client is ok");
            ChannelFuture future = bootstrap.connect("127.0.0.1", 6666)
                                          .sync();

            //对关闭通道进行监听
            future.channel()
                  .closeFuture()
                  .sync();
        } finally {
            eventExecutors.shutdownGracefully();
        }

    }
}