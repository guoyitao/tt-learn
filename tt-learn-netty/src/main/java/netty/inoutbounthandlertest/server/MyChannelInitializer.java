package netty.inoutbounthandlertest.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import netty.inoutbounthandlertest.MyByteToLongDecoder2;
import netty.inoutbounthandlertest.MyLongToByteEncoder;

/**
 * description:
 * date: 2020/9/11 17:51
 * created by: guo
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //入栈
        pipeline.addLast(new MyLongToByteEncoder());
        pipeline.addLast(new MyByteToLongDecoder2());
        pipeline.addLast(new MyServerHandler());
    }
}
