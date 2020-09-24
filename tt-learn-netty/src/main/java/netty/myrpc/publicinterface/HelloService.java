package netty.myrpc.publicinterface;

/**
 * 服务提供方和服务消费方都要用
 *
 * date: 2020/9/24 17:46
 * version:1.0
 * @author guoyitao
 */
public interface HelloService {
    String hello(String msg);
}
