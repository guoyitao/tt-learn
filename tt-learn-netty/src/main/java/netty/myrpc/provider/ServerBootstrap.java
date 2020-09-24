package netty.myrpc.provider;

import netty.myrpc.netty.NettyServer;

/**
 * user: guoyitao
 * date: 2020/9/24 17:50
 * version:1.0
 */
public class ServerBootstrap {

    public static void main(String[] args) {
        NettyServer.startServer("127.0.0.1",8001);

    }
}
