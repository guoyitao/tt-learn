package netty.inoutbounthandlertest.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import netty.inoutbounthandlertest.MyByteToLongDecoder2;
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
        pipeline.addLast(new MyByteToLongDecoder2());
        pipeline.addLast(new SimpleChannelInboundHandler<Long>() {

            @Override
            protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
                System.out.println("server said: " + msg);
            }

            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                System.out.println("send");
                ctx.writeAndFlush(1211111111111113456l);






                /*
                * decoder 被调用多次
                * aaaaaaaaaaaaaaaa是16个字节
                * 因为decoder每次读8个字节 所以调用2次
                *
                * MyLongToByteEncoder 的父类 MessageToByteEncoder 有一个write方法
                *
                *  @Override
                    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                        ByteBuf buf = null;
                        try {
                            if (acceptOutboundMessage(msg)) { //是不是应该编码的类型，如果不是就发给下一个handler不编码
                                @SuppressWarnings("unchecked")
                                I cast = (I) msg;
                                buf = allocateBuffer(ctx, cast, preferDirect);
                                try {
                                    encode(ctx, cast, buf);
                                } finally {
                                    ReferenceCountUtil.release(cast);
                                }

                                if (buf.isReadable()) {
                                    ctx.write(buf, promise);
                                } else {
                                    buf.release();
                                    ctx.write(Unpooled.EMPTY_BUFFER, promise);
                                }
                                buf = null;
                            } else {
                                ctx.write(msg, promise);
                            }
                        } catch (EncoderException e) {
                            throw e;
                        } catch (Throwable e) {
                            throw new EncoderException(e);
                        } finally {
                            if (buf != null) {
                                buf.release();
                            }
                        }
                    }
                * 因此 编写encoder要注意数据传入的类型，如果没对上，就不会执行encode方法，而是直接ctx.write(msg, promise);
                * */
//                ctx.writeAndFlush(Unpooled.copiedBuffer("aaaaaaaaaaaaaaaa", CharsetUtil.UTF_8));

            }
        });
    }
}
