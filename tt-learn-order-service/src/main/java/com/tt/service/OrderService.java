package com.tt.service;

import com.tt.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional(rollbackFor = RuntimeException.class)
    public void insertOrder(Order order) {
        String sql = "insert into orderaa VALUES(?,?,?,?)";
        int update = jdbcTemplate.update(sql, order.getOrderId(), order.getUserId(), order.getOrderContent(), order.getCreateTime());
        if (update != 1) {
            throw new RuntimeException("订单插入失败");
        }

        String result = dispatcherOrder(order.getOrderId());
        if (!"success".equals(result)) {
            throw new RuntimeException("创建订单失败，远程调用派发服务失败");
        }
    }

    public String dispatcherOrder(String orderId) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(2000);
        factory.setConnectTimeout(3000);

        RestTemplate restTemplate = new RestTemplate(factory);
        return restTemplate.getForObject("http://localhost:8082/dispatcher?orderId=" + orderId, String.class);

    }
}
