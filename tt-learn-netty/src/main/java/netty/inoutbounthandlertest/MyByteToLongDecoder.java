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


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //long 8个字节
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }
}
