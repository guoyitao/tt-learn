package com.example.demo;

import com.example.demo.service.direct.DirectOrderService;
import com.example.demo.service.fanout.OrderService;
import com.example.demo.service.topic.TopicOrderService;
import com.example.demo.service.ttl.TTLTopicOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class DemoApplicationTests {


    @Autowired
    OrderService fanout;

    @Test
    void fanoutTest() {
        fanout.mkOrder(UUID.randomUUID().toString(),UUID.randomUUID().toString(),1);
    }


    @Autowired
    DirectOrderService directOrderService;

    @Test
    void directOrder() {
        directOrderService.mkOrder(UUID.randomUUID().toString(),UUID.randomUUID().toString(),1);
    }


    @Autowired
    TopicOrderService topicOrderService;

    @Test
    void testTopicOrder() {
        topicOrderService.mkOrder(UUID.randomUUID().toString(),UUID.randomUUID().toString(),1);
    }


    @Autowired
    TTLTopicOrderService ttlTopicOrderService;

    @Test
    void testTTLTopicOrder() {
        for (int i = 0; i < 100000; i++) {
            ttlTopicOrderService.mkOrder(UUID.randomUUID().toString(),UUID.randomUUID().toString(),1);
        }

    }
    @Test
    void testmkTTLMessageOrder() {
        ttlTopicOrderService.mkTTLMessageOrder(UUID.randomUUID().toString(),UUID.randomUUID().toString(),1);
    }
}
