package netty.myrpc.provider;

import netty.myrpc.publicinterface.HelloService;

/**
 * user: guoyitao
 * date: 2020/9/24 17:48
 * version:1.0
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String msg) {
        System.out.println("收到client say" + msg);
        String result = "";
        if (msg != null) {
            result = "你好啊 收到了你说的" + msg;
        }
        return result;
    }
}
