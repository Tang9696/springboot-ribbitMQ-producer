package com.example.springbootribbitmqproducer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeadRabbitMqConfiguration {
    //1.声明注册fanout模式的交换机
    @Bean
    public DirectExchange deadExchange(){
        return new DirectExchange("dead-direct-exchange",true,false);
    }
    //2.声明队列
    @Bean
    public Queue deadQueue(){
        return new Queue("dead-direct-que",true,false,false);
    }
    //3.完成绑定
    @Bean
    public Binding deaddirectbind(){
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with("dead");
    }
}
