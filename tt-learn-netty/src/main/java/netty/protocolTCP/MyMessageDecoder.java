package netty.protocolTCP;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * 编码器
 * user: guoyitao
 * date: 2020/9/20 17:17
 * version:1.0
 */
public class MyMessageDecoder extends ReplayingDecoder<MessageProtocol> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyMessageDecoder decode被调用");
        int len = in.readInt();
        byte[] content = new byte[len];

        in.readBytes(content);

        //传递到下个handler
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(len);
        messageProtocol.setContent(content);

        out.add(messageProtocol);

    }
}
