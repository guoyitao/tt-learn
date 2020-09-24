package netty.myrpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * user: guoyitao
 * date: 2020/9/24 18:08
 * version:1.0
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context;
    private String result; //返回的结果
    private String para; //客服端调用远程的入参

    //1连接创建成功
    @Override
    public  void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }
    //4
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result = msg.toString();

        notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
    //3
    @Override
    public synchronized Object call() throws Exception {
        context.writeAndFlush(para);
        wait(); //channelRead被调用然后唤醒当前线程
        return result;
    }
    //2
    public void setPara(String para) {
        this.para = para;
    }
}
