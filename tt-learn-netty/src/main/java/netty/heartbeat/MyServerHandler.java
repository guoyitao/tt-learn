package netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * description:
 * date: 2020/9/11 13:26
 * created by: guo
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            String eventType = "";
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()){
                case READER_IDLE:
                    eventType ="读空闲";
                    break;
                case WRITER_IDLE:
                    eventType = "写空闲";
                    break;
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;
            }
            System.out.println(ctx.channel().remoteAddress() + "---超时时间" + eventType);
            ctx.writeAndFlush(eventType);
        }
    }
}
