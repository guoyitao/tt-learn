package com.tt;

import com.tt.pojo.Order;
import com.tt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * @author guoyitao
 */
@SpringBootApplication
@RestController
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Autowired
    OrderService orderService;


    @GetMapping("order")
    public Object order(@RequestParam(value = "orderId") String orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setCreateTime(new Date());
        order.setOrderContent("汉堡" + orderId);
        order.setUserId(UUID.randomUUID().toString());
        orderService.insertOrder(order);
        return "success";
    }

}
