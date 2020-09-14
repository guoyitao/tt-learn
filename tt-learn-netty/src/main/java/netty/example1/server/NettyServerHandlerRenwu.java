package netty.example1.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Date 2020/9/7 22:44
 * @Created by guo
 */
public class NettyServerHandlerRenwu extends ChannelInboundHandlerAdapter {

    //读取客户端发送的实际信息
    //这里处理异步任务
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //耗时任务 到taskQueue
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("server 耗时任务开始");
                    Thread.sleep(5000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello 你好！耗时任务结束1",CharsetUtil.UTF_8));
                    System.out.println("server 耗时任务结束");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //这个要10秒
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("server 耗时任务开始");
                    Thread.sleep(5000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello 你好！耗时任务结束2",CharsetUtil.UTF_8));
                    System.out.println("server 耗时任务结束");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //提交到 scheduledTaskQueue
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("server  scheduledTaskQueue耗时任务开始");
                    Thread.sleep(5000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello 你好！耗时任务结束3",CharsetUtil.UTF_8));
                    System.out.println("server  scheduledTaskQueue耗时任务结束");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },5, TimeUnit.SECONDS);

        System.out.println("go on");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将数据接入到缓存
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 你好！",CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        ctx.close();
    }
}
