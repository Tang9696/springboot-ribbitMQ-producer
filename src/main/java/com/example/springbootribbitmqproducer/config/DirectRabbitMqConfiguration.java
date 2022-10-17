package com.example.springbootribbitmqproducer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitMqConfiguration {
    //1.声明注册fanout模式的交换机
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct-exchange",true,false);
    }
    //2.声明队列
    @Bean
    public Queue directsmsQueue(){
        return new Queue("direct-smsquu",true);
    }
    @Bean
    public Queue directemailQueue(){
        return new Queue("direct-emailquu",true);
    }
    @Bean
    public Queue directwechatQueue(){
        return new Queue("direct-wechatquu",true);
    }
    //3.完成绑定
    @Bean
    public Binding directsmsbind(){
        return BindingBuilder.bind(directsmsQueue()).to(directExchange()).with("sms");
    }
    @Bean
    public Binding directemailbind(){
        return BindingBuilder.bind(directemailQueue()).to(directExchange()).with("email");
    }
    @Bean
    public Binding directwechatbind(){
        return BindingBuilder.bind(directwechatQueue()).to(directExchange()).with("wechat");
    }
}
