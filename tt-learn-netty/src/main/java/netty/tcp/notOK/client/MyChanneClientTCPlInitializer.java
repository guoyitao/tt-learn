package netty.tcp.notOK.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * user: guoyitao
 * date: 2020/9/19 14:12
 * version:1.0
 */
public class MyChanneClientTCPlInitializer extends ChannelInitializer<SocketChannel> {



    @Override
    protected void initChannel(SocketChannel ch) throws Exception {


        ch.pipeline().addLast(new SimpleChannelInboundHandler<ByteBuf>() {
            volatile int count;
            @Override
            protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                byte[] bytes = new byte[msg.readableBytes()];
                msg.readBytes(bytes);

                //接收服务器回写的uuid
                String message = new String(bytes, Charset.forName("utf-8"));
                System.out.println("client receive:  " +message);
                System.out.println("count:  " +(++this.count));
            }

            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                //发送10条消息
                for (int i = 0; i < 10; i++) {
                    ByteBuf byteBuf = Unpooled.copiedBuffer("hello " + i, CharsetUtil.UTF_8);
                    ctx.writeAndFlush(byteBuf);
                }
            }
        });
    }
}
