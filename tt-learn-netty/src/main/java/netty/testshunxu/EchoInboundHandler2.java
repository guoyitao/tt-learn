package netty.testshunxu;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @ClassName EchoInboundHandler2
 * @Description TODO
 * @Author felix
 * @Date 2019/9/27 15:35
 * @Version 1.0
 **/
public class EchoInboundHandler2 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("进入 EchoInboundHandler2.channelRead");

        String data = ((ByteBuf) msg).toString(CharsetUtil.UTF_8);
        System.out.println("EchoInboundHandler2.channelRead 接收到数据：" + data);
//        ctx.writeAndFlush(Unpooled.copiedBuffer("[第一次write] [EchoInboundHandler2] " + data, CharsetUtil.UTF_8));
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer("测试一下channel().writeAndFlush", CharsetUtil.UTF_8));
        ctx.fireChannelRead(Unpooled.copiedBuffer("[EchoInboundHandler2] " + data, CharsetUtil.UTF_8));

        System.out.println("退出 EchoInboundHandler2 channelRead");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[EchoInboundHandler2.channelReadComplete]读取数据完成");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("[EchoInboundHandler2.exceptionCaught]");
    }
}