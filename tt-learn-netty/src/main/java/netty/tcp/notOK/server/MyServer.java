package netty.tcp.notOK.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * description:
 * date: 2020/9/11 17:50
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
                     .option(ChannelOption.SO_BACKLOG,128)
                     .childOption(ChannelOption.SO_KEEPALIVE,true)
                     .childHandler(new MyChannelServerTCPInitializer());

            System.out.println("start on "+ port);
            ChannelFuture future = bootstrap.bind(port)
                                            .sync();
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {
        new MyServer(8888).run();;
    }
}
