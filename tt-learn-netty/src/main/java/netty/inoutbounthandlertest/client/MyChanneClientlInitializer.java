package netty.inoutbounthandlertest.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import netty.inoutbounthandlertest.MyLongToByteEncoder;

/**
 * description:
 * date: 2020/9/11 17:51
 * created by: guo
 */
public class MyChanneClientlInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //入栈
        pipeline.addLast(new MyLongToByteEncoder());

        pipeline.addLast(new SimpleChannelInboundHandler<Long>() {

            @Override
            protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {

            }

            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                System.out.println("send");
                ctx.writeAndFlush(123456);
            }
        });
    }
}
