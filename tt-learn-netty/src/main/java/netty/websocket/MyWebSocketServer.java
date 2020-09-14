package netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * description:
 * date: 2020/9/11 13:17
 * created by: guo
 */
public class MyWebSocketServer {
    private int port;

    public MyWebSocketServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(bossGroup, workerGroup)
                     .channel(NioServerSocketChannel.class)
                     .handler(new LoggingHandler(LogLevel.INFO))
                     .childHandler(new ChannelInitializer<SocketChannel>() {
                         @Override
                         protected void initChannel(SocketChannel ch) throws Exception {
                             ChannelPipeline pipeline = ch.pipeline();

                             //websocket协议本身是基于http协议的，所以这边也要使用http解编码器
                             pipeline.addLast(new HttpServerCodec());
                             //以块的方式来写的处理器
                             pipeline.addLast(new ChunkedWriteHandler());
                             //netty是基于分段请求的，HttpObjectAggregator的作用是将请求分段再聚合,参数是聚合字节的最大长度
                             pipeline.addLast(new HttpObjectAggregator(8192));

                             //ws://server:port/context_path
                             //ws://localhost:9999/ws
                             //参数指的是contex_path
                             pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
                             //websocket定义了传递数据的6中frame类型
                             pipeline.addLast(new MyWebSocketFrameHandler());
                         }
                     });

            System.out.println("start on " + port);
            ChannelFuture future = bootstrap.bind(80)
                                            .sync();
            future.channel()
                  .closeFuture()
                  .sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {
        new MyWebSocketServer(7777).run();
    }
}
