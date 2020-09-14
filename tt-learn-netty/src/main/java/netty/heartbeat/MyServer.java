package netty.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * description:
 * date: 2020/9/11 13:17
 * created by: guo
 */
public class MyServer {
    private int port;

    public MyServer(int port) {
        this.port = port;
    }

    public void run() throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(bossGroup,workerGroup)
                     .channel(NioServerSocketChannel.class)
                     .handler(new LoggingHandler(LogLevel.INFO))
                     .childHandler(new ChannelInitializer<SocketChannel>() {
                         @Override
                         protected void initChannel(SocketChannel ch) throws Exception {
                             ChannelPipeline pipeline = ch.pipeline();
                             pipeline.addLast("decoder",new StringDecoder());
                             pipeline.addLast("encoder",new StringEncoder());
                             pipeline.addLast("idleHandler",new IdleStateHandler(3,5,7, TimeUnit.SECONDS));
                                /*
                                * readerIdleTime 多久没读
                                * writerIdleTime 多久没写
                                * allIdleTime 多久没读写
                                * 就发送心跳包
                                * */
                            pipeline.addLast(new MyServerHandler());
                         }
                     });

            System.out.println("start on "+ port);
            ChannelFuture future = bootstrap.bind(80)
                                            .sync();
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {
        new MyServer(7777).run();
    }
}
