package netty.myrpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.myrpc.provider.HelloServiceImpl;

/**
 * user: guoyitao
 * date: 2020/9/24 18:01
 * version:1.0
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("msg= " + msg);
        /*
        * 协议 每个消息以ttno996 开头
        * */
        if (msg.toString().startsWith("ttno996#")) {
            String value = msg.toString()
                             .substring(msg.toString()
                                           .indexOf("#")+1);

            String result = new HelloServiceImpl().hello(value);
            ctx.writeAndFlush(result);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
