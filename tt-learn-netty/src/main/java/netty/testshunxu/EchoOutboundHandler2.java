package netty.testshunxu;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @ClassName EchoOutboundHandler2
 * @Description TODO
 * @Author felix
 * @Date 2019/9/27 15:36
 * @Version 1.0
 **/
public class EchoOutboundHandler2 extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("进入 EchoOutboundHandler2.write");

//        ctx.writeAndFlush(Unpooled.copiedBuffer("[第二次write中的write]", CharsetUtil.UTF_8));
        ctx.write(msg);

        System.out.println("退出 EchoOutboundHandler2.write");
    }
}