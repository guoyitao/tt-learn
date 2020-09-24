package netty.longtimejobexample.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Description
 * @Date 2020/9/7 22:26
 * @Created by guo
 */
public class NettyServer {
    public static void main(String[] args) throws Exception {


        //bossGroup只处理连接请求,    真正的和客户端的业务处理,会交给 workerGroup
        //两个都是无限循环
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(bossGroup, workerGroup)
                     .channel(NioServerSocketChannel.class)
                     .option(ChannelOption.SO_BACKLOG, 128) //线程队列得到连接个数
                     .childOption(ChannelOption.SO_KEEPALIVE, true) //设置保持活动连接状态
                     .childHandler(new ChannelInitializer<SocketChannel>() {
                         @Override
                         protected void initChannel(SocketChannel ch) throws Exception {
                             System.out.println("客户channel hashcode=" + ch.hashCode());

                             ch.pipeline()
                               //                           .addLast(new NettyServerHandler());
                               .addLast(new NettyServerHandlerRenwu());
                         }

                     });
            System.out.println("server ready");

            //启动服务器  绑定端口
            ChannelFuture future = bootstrap.bind(6666)
                                            .sync();

            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("success");
                    }
                    if (future.isDone()){
                        System.out.println("done");
                    }
                }
            });
            //对关闭通道进行监听
            future.channel()
                  .closeFuture()
                  .sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
