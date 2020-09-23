package netty.inoutbounthandlertest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * description:
 * date: 2020/9/11 17:53
 * created by: guo
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {

    /*
     *
     * 会被多次调用，指导没有新的元素被添加到out，或者in没有可读字节
     * 如果out不为空，会将list的内容传递给下一个channelInBoundHandler,改处理器的方法也会被调用多次
     *
     * @author guo
     * @date 2020/9/17 17:58
     * @param ctx
     * @param in
     * @param out
     * @return void
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByteToLongDecoder decode 被调用");
        //long 8个字节  一定要先判断 否则结果可能与预期的不一致
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }
}
