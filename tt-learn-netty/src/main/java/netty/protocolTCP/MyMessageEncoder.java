package netty.protocolTCP;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码器
 * user: guoyitao
 * date: 2020/9/20 17:17
 * version:1.0
 */
public class MyMessageEncoder extends MessageToByteEncoder<MessageProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, ByteBuf out) throws Exception {
        System.out.println("MyMessageEncoder encode被调用");
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());
    }
}
