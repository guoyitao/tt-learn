package netty.example1.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @Description
 * @Date 2020/9/7 23:01
 * @Created by guo
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    //通道就绪
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client" +ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 服务端你好！", CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("server say: " + buf.toString(CharsetUtil.UTF_8) + "  address: " + ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        ctx.close();
    }
}