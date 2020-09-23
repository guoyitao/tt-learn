package netty.protocolTCP.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import netty.protocolTCP.MessageProtocol;
import netty.protocolTCP.MyMessageDecoder;
import netty.protocolTCP.MyMessageEncoder;

import java.nio.charset.Charset;

/**
 * user: guoyitao
 * date: 2020/9/19 14:12
 * version:1.0
 */
public class MyChanneClientProtocolTCPlInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyMessageDecoder());
        pipeline.addLast(new MyMessageEncoder());
        pipeline.addLast(getSimpleChannelInboundHandler());
    }

    private SimpleChannelInboundHandler<MessageProtocol> getSimpleChannelInboundHandler() {
        return new SimpleChannelInboundHandler<MessageProtocol>() {
            volatile int count;
            @Override
            protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
                int len = msg.getLen();
                byte[] content = msg.getContent();
                System.out.println("客户端接收到 len:" + len + "content: " + new String(content, Charset.forName("utf-8")) + " 消息包数量：" + (++count));
            }

            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                for (int i = 0; i < 5; i++) {
                    String msg = "你好啊" +i;
                    byte[] content = msg.getBytes(Charset.forName("utf-8"));
                    MessageProtocol messageProtocol = new MessageProtocol();
                    messageProtocol.setContent(content);
                    messageProtocol.setLen(content.length);
                    ctx.writeAndFlush(messageProtocol);
                }
            }

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                System.out.println("异常信息=" +cause.getMessage());
                ctx.close();
            }
        };
    }
}
