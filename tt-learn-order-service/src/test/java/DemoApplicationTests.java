import com.tt.OrderServiceApplication;
import com.tt.pojo.Order;
import com.tt.service.MQOrderService;
import com.tt.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

@SpringBootTest(classes = OrderServiceApplication.class)
public class DemoApplicationTests {

    @Autowired
    OrderService orderService;

    @Test
    public void testOrderService(){
        Order order = new Order();
        order.setOrderId(String.valueOf(10086));
        order.setCreateTime(new Date());
        order.setOrderContent("汉堡");
        order.setUserId(UUID.randomUUID().toString());
        orderService.insertOrder(order);
    }

    @Autowired
    MQOrderService mqOrderService;
    @Test
    public void testMQOrderService(){
        Order order = new Order();
        order.setOrderId(String.valueOf(6));
        order.setCreateTime(new Date());
        order.setOrderContent("汉堡");
        order.setUserId(UUID.randomUUID().toString());
        mqOrderService.insertOrder(order);
    }

}
