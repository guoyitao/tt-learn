异步执行任务的方法

#### 1、加入到workerGroup的事件循环队列的线程池里面

`ctx.channel().eventLoop().execute()`

#### 2、在handler里面创建一个EventExecutorGroup，然后把任务提交到里面

业务线程池:
`static final EventExecutorGroup group = new DefaultEventLoopGroup(16);`
提交耗时任务
`group.submit()`


#### 3、把handler全部托管给业务线程
业务线程：
`
static final EventExecutorGroup group = new DefaultEventLoopGroup(2);`

初始化的时候设置：
`pipline.addLast(group,serverHandler);`



结论：第2种灵活，还可以在里面做访问数据库，rpc调用其他客户端，第三种死板

# 源码理解的

netty如何区分是把任务加入到workerGroup的eventLoop还是3、XX里面自定义的业务线程

`static void invokeChannelRead(final AbstractChannelHandlerContext next, Object msg) {
        final Object m = next.pipeline.touch(ObjectUtil.checkNotNull(msg, "msg"), next);
        EventExecutor executor = next.executor();
        if (executor.inEventLoop()) { //提交任务到eventloop
            next.invokeChannelRead(m);
        } else { //如果初始化handler的时候添加了group那么就会执行下面的
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    next.invokeChannelRead(m);
                }
            });
        }
    }`