package netty.inoutbounthandlertest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * user: guoyitao
 * date: 2020/9/17 20:26
 * version:1.0
 */
public class MyByteToLongDecoder2 extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByteToLongDecoder2 decode 被调用");

        //不需要判断 是否可够读 ReplayingDecoder裡面解決了判斷
        out.add(in.readLong());
    }
}
