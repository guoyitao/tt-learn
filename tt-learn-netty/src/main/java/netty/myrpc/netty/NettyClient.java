package netty.myrpc.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * user: guoyitao
 * date: 2020/9/24 19:26
 * version:1.0
 */
public class NettyClient {
    private static ExecutorService executor = Executors.newFixedThreadPool(4);

    private static NettyClientHandler client;

    public Object getBean(final Class<?> serviceClass,final String providerName){
        return Proxy.newProxyInstance(Thread.currentThread()
                                            .getContextClassLoader(), new Class<?>[]{serviceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                if (client == null){
                    initClient();
                }

                client.setPara(providerName + args[0]);

                return executor.submit(client).get();
            }
        });
    }


    private static void initClient(){
        client = new NettyClientHandler();

        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                 .channel(NioSocketChannel.class)
                 .option(ChannelOption.TCP_NODELAY,true) //无延迟
                 .handler(new ChannelInitializer<SocketChannel>() {
                     @Override
                     protected void initChannel(SocketChannel ch) throws Exception {
                         ChannelPipeline pipeline = ch.pipeline();
                         pipeline.addLast(new StringDecoder());
                         pipeline.addLast(new StringEncoder());
                         pipeline.addLast(client);
                     }
                 });

        try {
            bootstrap.connect("127.0.0.1",8001).sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
