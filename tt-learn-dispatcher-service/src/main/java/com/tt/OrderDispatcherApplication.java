package com.tt;

import com.tt.service.OrderDispatcherService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author guoyitao
 */
@SpringBootApplication
@RestController
public class OrderDispatcherApplication {

    @Resource
    OrderDispatcherService orderDispatcherService;

    public static void main(String[] args) {
        SpringApplication.run(OrderDispatcherApplication.class, args);
    }

    @GetMapping("dispatcher")
    public Object order(@RequestParam(value = "orderId") String orderId) {
        orderDispatcherService.insertOrder(orderId);
        return "success";
    }
}
