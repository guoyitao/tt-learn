package netty.tcp.notOK.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * user: guoyitao
 * date: 2020/9/19 14:42
 * version:1.0
 */
public class MyChannelServerTCPInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new SimpleChannelInboundHandler<ByteBuf>() {
            //收到的次数
            volatile int count;

            @Override
            protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                byte[] bytes = new byte[msg.readableBytes()];
                msg.readBytes(bytes);

                String message = new String(bytes, Charset.forName("utf-8"));
                System.out.println("get:  " + message);
                System.out.println("count:" + (++this.count));

                //发送给客户端
                ByteBuf resp = Unpooled.copiedBuffer(UUID.randomUUID()
                                                         .toString() + "\n", Charset.forName("utf-8"));
                ctx.writeAndFlush(resp);

            }

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                cause.printStackTrace();
                ctx.close();
            }
        });

    }
}
