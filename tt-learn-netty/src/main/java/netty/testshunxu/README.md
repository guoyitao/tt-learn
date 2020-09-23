## 实验一：在InboundHandler中不触发fire方法，后续的InboundHandler还能顺序执行吗？

###### 测试入站时候，ChannelInboundHandler调用ctx.fireChannelRead对于顺序（时候会执行下一个handler）

###### 方法：EchoInboundHandler2在不调用ctx.fireChannelRead看看EchoInboundHandler时候会执行

![image-20200922211456413](https://gitee.com/no996/imgbase/raw/master/imgs/20200922211456.png)

结果：

> 
>
> EchoServer正在启动.
> EchoServer绑定端口8080
> 进入 EchoInboundHandler1.channelRead
> EchoInboundHandler1.channelRead 收到数据：111
> 进入 EchoInboundHandler2.channelRead
> EchoInboundHandler2.channelRead 接收到数据：[EchoInboundHandler1] 111
> 退出 EchoInboundHandler2 channelRead
> 退出 EchoInboundHandler1 channelRead
> [EchoInboundHandler1.channelReadComplete]

结论：InboundHandler是通过fire事件决定是否要执行下一个InboundHandler，如果哪个InboundHandler没有调用fire事件，那么往后的Pipeline就断掉了。

## 实验二：InboundHandler和OutboundHandler的执行顺序是什么？

![](https://gitee.com/no996/imgbase/raw/master/imgs/20200922211456.png)

在EchoInboundHandler2和EchoInboundHandler3都执行一次ctx.writeAndFlush();

加入Pipeline的ChannelHandler的顺序如上图所示，那么最后执行的顺序如何呢？执行结果如下：

![image-20200922213925673](https://gitee.com/no996/imgbase/raw/master/imgs/20200922213925.png)

InboundHandler1 => InboundHandler2 => **OutboundHandler1 => OutboundHander2 => OutboundHandler3** => InboundHandler3 =>**OutboundHandler1 => OutboundHander2 => OutboundHandler3**

#### `结论：`

`1、InboundHandler是按照Pipleline的加载顺序，**顺序**执行。`

`2、OutboundHandler是按照Pipeline的加载顺序，**逆序**执行。`

`3、每次writeAndFlush都会把OutboundHandler执行链跑一遍`

## **实验三：如果把OutboundHandler放在InboundHandler的后面，OutboundHandler会执行吗？**

![image-20200923111223693](https://gitee.com/no996/imgbase/raw/master/imgs/20200923111223.png)

结果：

![image-20200923111204131](https://gitee.com/no996/imgbase/raw/master/imgs/20200923111204.png)

由此可见，OutboundHandler没有执行，为什么呢？

因为Pipleline是执行完所有有效的InboundHandler，再返回执行在最后一个InboundHandler之前的OutboundHandler。

注意，有效的InboundHandler是指fire事件触达到的InboundHandler，如果某个InboundHandler没有调用fire事件，后面的InboundHandler都是无效的InboundHandler。

为了印证这一点，我们继续做一个实验，我们把其中一个OutboundHandler放在最后一个有效的InboundHandler之前，看看这唯一的一个OutboundHandler是否会执行，其他OutboundHandler是否不会执行。

![image-20200923112125439](https://gitee.com/no996/imgbase/raw/master/imgs/20200923112125.png)

结果： 我在EchoInboundHandler2里面执行了一次writeAndFlush所以调用了EchoOutboundHandler1

![image-20200923112229896](https://gitee.com/no996/imgbase/raw/master/imgs/20200923112229.png)

由此可见，只执行了OutboundHandler1，其他OutboundHandler没有被执行。

`**结论：**`

`1、有效的InboundHandler是指通过fire事件能触达到的最后一个InboundHander。`

`2、如果想让所有的OutboundHandler都能被执行到，那么必须把OutboundHandler放在最后一个有效的InboundHandler之前。`

`3、推荐的做法是通过addFirst加载所有OutboundHandler，再通过addLast加载所有InboundHandler。`

## **实验四：如果其中一个OutboundHandler没有执行write方法，那么消息会不会发送出去？**

OutboundHandler2的write方法注掉

```java
public class EchoOutboundHandler2 extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("进入 EchoOutboundHandler2.write");

//        ctx.writeAndFlush(Unpooled.copiedBuffer("[第二次write中的write]", CharsetUtil.UTF_8));
//        ctx.write(msg);

        System.out.println("退出 EchoOutboundHandler2.write");
    }
}
```

结果：

![image-20200923113233626](https://gitee.com/no996/imgbase/raw/master/imgs/20200923113233.png)

![image-20200923113312063](https://gitee.com/no996/imgbase/raw/master/imgs/20200923113312.png)

可以看到，OutboundHandler3并没有被执行到，另外，客户端也没有收到发送的消息

`结论：`

`1、OutboundHandler是通过write方法实现Pipeline的串联的。`

`2、如果OutboundHandler在Pipeline的处理链上，其中一个OutboundHandler没有调用write方法，最终消息将不会发送出去。`

## **实验五：ctx.writeAndFlush 的OutboundHandler的执行顺序是什么？**

![image-20200923115637325](https://gitee.com/no996/imgbase/raw/master/imgs/20200923115637.png)

在InboundHander2中调用ctx.writeAndFlush：

![image-20200923115702338](https://gitee.com/no996/imgbase/raw/master/imgs/20200923115702.png)

结果：



![image-20200923115817368](https://gitee.com/no996/imgbase/raw/master/imgs/20200923115817.png)

ctx.writeAndFlush是从当前的ChannelHandler开始，向前依次执行OutboundHandler的write方法，所以分别执行了OutboundHandler2和OutboundHandler3：

`结论：`

`1、ctx.writeAndFlush是从当前ChannelHandler开始，逆序向前执行OutboundHandler。`

`2、ctx.writeAndFlush所在ChannelHandler后面的OutboundHandler将不会被执行。`

## **实验六：ctx.channel().writeAndFlush 的OutboundHandler的执行顺序是什么？**

还是实验五的代码，不同之处只是把ctx.writeAndFlush修改为ctx.channel().writeAndFlush。

![image-20200923121714653](https://gitee.com/no996/imgbase/raw/master/imgs/20200923121714.png)

![image-20200923121950265](https://gitee.com/no996/imgbase/raw/master/imgs/20200923121950.png)

结果如下：

![image-20200923121939144](https://gitee.com/no996/imgbase/raw/master/imgs/20200923121939.png)

------

![image-20200923121740613](https://gitee.com/no996/imgbase/raw/master/imgs/20200923121740.png)

![image-20200923122043373](https://gitee.com/no996/imgbase/raw/master/imgs/20200923122043.png)

`由上图可知，所有OutboundHandler都执行了，由此我们得到结论：`

`1、ctx.channel().writeAndFlush 是从最后一个OutboundHandler开始，依次逆序向前执行其他OutboundHandler，即使最后一个ChannelHandler是OutboundHandler，在InboundHandler之前，也会执行该OutbondHandler。`

`2、千万不要在OutboundHandler的write方法里执行ctx.channel().writeAndFlush，否则就死循环了。`

# 总结

1. InboundHandler是通过fire事件决定是否要执行下一个InboundHandler，如果哪个InboundHandler没有调用fire事件，那么往后的Pipeline就断掉了。
2. InboundHandler是按照Pipleline的加载顺序，顺序执行。
3. OutboundHandler是按照Pipeline的加载顺序，逆序执行。
4. 有效的InboundHandler是指通过fire事件能触达到的最后一个InboundHander。
5. 如果想让所有的OutboundHandler都能被执行到，那么必须把OutboundHandler放在最后一个有效的InboundHandler之前。
6. 推荐的做法是通过addFirst加载所有OutboundHandler，再通过addLast加载所有InboundHandler。
7. OutboundHandler是通过write方法实现Pipeline的串联的。
8. 如果OutboundHandler在Pipeline的处理链上，其中一个OutboundHandler没有调用write方法，最终消息将不会发送出去。
9. ctx.writeAndFlush是从当前ChannelHandler开始，逆序向前执行OutboundHandler。
10. ctx.writeAndFlush所在ChannelHandler后面的OutboundHandler将不会被执行。
11. ctx.channel().writeAndFlush 是从最后一个OutboundHandler开始，依次逆序向前执行其他OutboundHandler，即使最后一个ChannelHandler是OutboundHandler，在InboundHandler之前，也会执行该OutbondHandler。
12. 千万不要在OutboundHandler的write方法里执行ctx.channel().writeAndFlush，否则就死循环了。