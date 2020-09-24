/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package netty.longtimejobexample.echo2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.Charset;

/**
 * Handler implementation for the echo server.
 */
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    //业务线程池
    static final EventExecutorGroup group = new DefaultEventLoopGroup(16);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws InterruptedException {


        System.out.println("EchoServerHandler 当前线程:" + Thread.currentThread()
                                                             .getName());

//        //耗时任务 到taskQueue
//        ctx.channel().eventLoop().execute(() -> {
//            try {
//                System.out.println("execute 耗时任务 当前线程:" +Thread.currentThread().getName());
//                System.out.println("server 耗时任务开始1");
//                Thread.sleep(5000);
//                ctx.writeAndFlush(Unpooled.copiedBuffer("hello 你好！耗时任务结", CharsetUtil.UTF_8));
//                System.out.println("server 耗时任务结束1");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//
//        //耗时任务 到taskQueue
//        ctx.channel().eventLoop().execute(() -> {
//            try {
//                System.out.println("execute 耗时任务 当前线程:" +Thread.currentThread().getName());
//                System.out.println("server 耗时任务开始2");
//                Thread.sleep(5000);
//                ctx.writeAndFlush(Unpooled.copiedBuffer("hello 你好！耗时任务结", CharsetUtil.UTF_8));
//                System.out.println("server 耗时任务结束2");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });

//        group.submit(new Callable<Object>() {
//            @Override
//            public Object call() throws Exception {
//                //接受客户端消息
//                ByteBuf byteBuf = (ByteBuf) msg;
//                byte[] aByte = new byte[byteBuf.readableBytes()];
//                byteBuf.readBytes(aByte);
//                System.out.println(new String(aByte, Charset.forName("utf-8")));
//
//                Thread.sleep(5*1000);
//
//                System.out.println("group sunbit thread is "+ Thread.currentThread().getName());
//                ctx.writeAndFlush(Unpooled.copiedBuffer("hello client this guo", CharsetUtil.UTF_8));
//
//
//                return null;
//            }
//        });
//
//        group.submit(new Callable<Object>() {
//            @Override
//            public Object call() throws Exception {
//                //接受客户端消息
//                ByteBuf byteBuf = (ByteBuf) msg;
//                byte[] aByte = new byte[byteBuf.readableBytes()];
//                byteBuf.readBytes(aByte);
//                System.out.println(new String(aByte, Charset.forName("utf-8")));
//
//                Thread.sleep(5*1000);
//
//                System.out.println("group sunbit thread is "+ Thread.currentThread().getName());
//                ctx.writeAndFlush(Unpooled.copiedBuffer("hello client this guo", CharsetUtil.UTF_8));
//
//
//                return null;
//            }
//        });

//        接受客户端消息
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] aByte = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(aByte);
        System.out.println(new String(aByte, Charset.forName("utf-8")));

        Thread.sleep(5 * 1000);

        System.out.println("group sunbit thread is " + Thread.currentThread()
                                                             .getName());
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello client this guo", CharsetUtil.UTF_8));

    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
