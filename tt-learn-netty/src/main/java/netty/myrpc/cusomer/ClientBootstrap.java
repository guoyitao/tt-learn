package netty.myrpc.cusomer;

import netty.myrpc.netty.NettyClient;
import netty.myrpc.publicinterface.HelloService;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * user: guoyitao
 * date: 2020/9/24 19:46
 * version:1.0
 */
public class ClientBootstrap {

    public static final String providerName = "ttno996#";
    private static volatile AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) {

        NettyClient nettyClient = new NettyClient();

        HelloService bean = (HelloService) nettyClient.getBean(HelloService.class, providerName);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(count);
                System.exit(-1);
            }
        }).start();

        while (true){
            System.out.println(bean.hello("hello rpc！this tt"));
            count.incrementAndGet();
        }


    }
}
