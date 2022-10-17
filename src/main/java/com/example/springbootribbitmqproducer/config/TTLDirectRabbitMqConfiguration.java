package com.example.springbootribbitmqproducer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TTLDirectRabbitMqConfiguration {
    //1.声明注册fanout模式的交换机
    @Bean
    public DirectExchange ttldirectExchange(){
        return new DirectExchange("ttl-direct-exchange",true,false);
    }
    //2.声明队列
    @Bean
    public Queue ttldirectsmsQueue(){
        //设置队列失效时间
        Map<String,Object> args = new HashMap<>();
        args.put("x-message-ttl",5000);
        //设置队列最大消息接收数，超出该数量的消息直接进入死信队列
        //args.put("x-max-length",20);
        //配置绑定的死信队列
        args.put("x-dead-letter-exchange","dead-direct-exchange");
        //如果你是fanout模式可以不用配置该条代码，我们是direct模式，需要指定路由key
        args.put("x-dead-letter-routing-key","dead");
        return new Queue("ttl-direct-smsquu",true,false,false,args);
    }
    @Bean
    public Queue ttldirectemailQueue(){
        //设置队列失效时间
        Map<String,Object> args = new HashMap<>();
        args.put("x-message-ttl",5000);
        //配置绑定的死信队列
        args.put("x-dead-letter-exchange","dead-direct-exchange");
        //如果你是fanout模式可以不用配置该条代码，我们是direct模式，需要指定路由key
        args.put("x-dead-letter-routing-key","dead");
        return new Queue("ttl-direct-emailquu",true,false,false,args);
    }
    @Bean
    public Queue ttldirectwechatQueue(){
        return new Queue("ttl-direct-wechatquu",true,false,false);
    }

    //3.完成绑定
    @Bean
    public Binding ttldirectsmsbind(){
        return BindingBuilder.bind(ttldirectsmsQueue()).to(ttldirectExchange()).with("ttl-sms");
    }
    @Bean
    public Binding ttldirectemailbind(){
        return BindingBuilder.bind(ttldirectemailQueue()).to(ttldirectExchange()).with("ttl-email");
    }
    @Bean
    public Binding ttldirectwechatbind(){
        return BindingBuilder.bind(ttldirectwechatQueue()).to(ttldirectExchange()).with("ttl-wechat");
    }
}
