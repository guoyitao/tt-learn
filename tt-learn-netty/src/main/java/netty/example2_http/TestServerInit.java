package netty.example2_http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * description:
 * date: 2020/9/8 21:55
 * created by: guo
 */
public class TestServerInit extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("myHttpServerCodec",new HttpServerCodec());
        pipeline.addLast("myHttpServerHandler",new TestHttpServerHandler());
    }
}
