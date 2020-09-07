package echo1.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup(); //3.创建 EventLoopGroup
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group) //4.创建 ServerBootstrap
                           .channel(NioServerSocketChannel.class) //5.指定使用 NIO 的传输 Channel
                           .localAddress(new InetSocketAddress(port)) //6.设置 socket 地址使用所选的端口
                           .childHandler(new ChannelInitializer<SocketChannel>() { //7.添加 EchoServerHandler 到 Channel 的 ChannelPipeline

                               @Override
                               protected void initChannel(SocketChannel ch) throws Exception {
                                   ch.pipeline()
                                     .addLast(new EchoServerHandler());
                               }
                           });

            ChannelFuture future = serverBootstrap.bind()
                                                .sync(); //8.绑定的服务器;sync 等待服务器关闭
            System.out.println(EchoServer.class.getName() + " started and listen on " + future.channel().localAddress());
            future.channel().closeFuture().sync();            //9.关闭 channel 和 块，直到它被关闭
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully().sync(); //10.关闭 EventLoopGroup，释放所有资源。
        }
    }

    public static void main(String[] args) throws Exception {
        new EchoServer(9999).start(); //2.呼叫服务器的 start() 方法
    }
}
