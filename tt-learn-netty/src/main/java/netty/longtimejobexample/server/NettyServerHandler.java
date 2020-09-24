package netty.longtimejobexample.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

/**
 * @Description
 * @Date 2020/9/7 22:44
 * @Created by guo
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    //读取客户端发送的实际信息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("线程：" + Thread.currentThread().getName());
        System.out.println("server ctx = " + ctx);

        System.out.println("看看channel和pipline的关系");
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline();

        ByteBuf buf = (ByteBuf) msg;
        System.out.println("client say: " + buf.toString(CharsetUtil.UTF_8) + "  address: " + ctx.channel().remoteAddress());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将数据接入到缓存
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 你好！",CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        ctx.close();
    }
}
