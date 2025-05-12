package rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

public class ProducerExample {
    public static void main(String[] args) throws Exception {
        // 显式指定生产者组名称
        DefaultMQProducer producer = new DefaultMQProducer("pg_order_svc");

        // 设置NameServer地址
        producer.setNamesrvAddr("10.142.255.4:9876");

        // 启动生产者实例
        producer.start();

        // 发送消息
        Message msg = new Message("chbn_book_order_print", "success", "Hello RocketMQ".getBytes());
        producer.send(msg);

        // 关闭生产者
        producer.shutdown();
    }
}

