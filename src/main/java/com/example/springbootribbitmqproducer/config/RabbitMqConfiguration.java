package com.example.springbootribbitmqproducer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {
    //1.声明注册fanout模式的交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout-exchange",true,false);
    }
    //2.声明队列
    @Bean
    public Queue smsQueue(){
        return new Queue("smsquu",true);
    }
    @Bean
    public Queue emailQueue(){
        return new Queue("emailquu",true);
    }
    @Bean
    public Queue wechatQueue(){
        return new Queue("wechatquu",true);
    }
    //3.完成绑定
    @Bean
    public Binding smsbind(){
        return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }
    @Bean
    public Binding emailbind(){
        return BindingBuilder.bind(emailQueue()).to(fanoutExchange());
    }
    @Bean
    public Binding wechatbind(){
        return BindingBuilder.bind(wechatQueue()).to(fanoutExchange());
    }
}
