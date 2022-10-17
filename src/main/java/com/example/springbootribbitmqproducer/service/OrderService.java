package com.example.springbootribbitmqproducer.service;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void makeOrder(String userid,String productid,int num) {
        //1.根据商品id查询库存是否充足
        //2.保存订单
        String orderid = UUID.randomUUID().toString();
        System.out.println("orderid:"+ orderid);
        //3.通过MQ来完成消息的分发
        //参数是：交换机，路由key,消息
        String exchangename = "fanout-exchange";
        rabbitTemplate.convertAndSend(exchangename,"",orderid);
        rabbitTemplate.convertAndSend(exchangename,"",orderid);
    }

    public void makeOrderDirect(String userid,String productid,int num) {
        //1.根据商品id查询库存是否充足
        //2.保存订单
        String orderid = UUID.randomUUID().toString();
        System.out.println("orderid:"+ orderid);
        //3.通过MQ来完成消息的分发
        //参数是：交换机，路由key,消息
        String exchangename = "direct-exchange";
        String routekey1 ="sms";
        String routekey2 ="email";
        rabbitTemplate.convertAndSend(exchangename,routekey1,orderid);
        rabbitTemplate.convertAndSend(exchangename,routekey2,orderid);
    }

    public void makeOrderTopic(String userid,String productid,int num) {
        //1.根据商品id查询库存是否充足
        //2.保存订单
        String orderid = UUID.randomUUID().toString();
        System.out.println("orderid:"+ orderid);
        //3.通过MQ来完成消息的分发
        //参数是：交换机，路由key,消息
        String exchangename = "topic-exchange";
        String routekey1 = "com.sms";
        rabbitTemplate.convertAndSend(exchangename,routekey1,orderid);
    }

    public void makeOrderTTLDirect(String userid,String productid,int num) {
        //1.根据商品id查询库存是否充足
        //2.保存订单
        String orderid = UUID.randomUUID().toString();
        System.out.println("orderid:"+ orderid);
        //3.通过MQ来完成消息的分发
        //参数是：交换机，路由key,消息
        String exchangename = "ttl-direct-exchange";
        String routekey1 = "ttl-sms";
        String routekey2 = "ttl-email";
        rabbitTemplate.convertAndSend(exchangename,routekey1,orderid);
        rabbitTemplate.convertAndSend(exchangename,routekey2,orderid);
    }

    public void makeOrderTTLMessage(String userid,String productid,int num) {
        //1.根据商品id查询库存是否充足
        //2.保存订单
        String orderid = UUID.randomUUID().toString();
        System.out.println("orderid:"+ orderid);
        //3.通过MQ来完成消息的分发
        //参数是：交换机，路由key,消息
        String exchangename = "ttl-direct-exchange";
        String routekey1 = "ttl-wechat";
        //给消息设置过期时间
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("5000");
                message.getMessageProperties().setContentEncoding("UTF-8");
                return message;
            }
        };
        rabbitTemplate.convertAndSend(exchangename,routekey1,orderid,messagePostProcessor);

    }
}
