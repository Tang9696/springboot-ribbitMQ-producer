package com.example.springbootribbitmqproducer;

import com.example.springbootribbitmqproducer.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootRibbitMqProducerApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    void contextLoads() {
        orderService.makeOrder("1","1",12);
    }

    @Test
    void contextLoads2() {
        orderService.makeOrderDirect("2","2",122);
    }

    @Test
    void contextLoads3() {
        orderService.makeOrderTopic("23","22",1222);
    }

    @Test
    void contextLoads4() {
        orderService.makeOrderTTLDirect("25","223",1777);
    }

    @Test
    void contextLoads5() {
        orderService.makeOrderTTLMessage("2577","22343534",178877);
    }


}
