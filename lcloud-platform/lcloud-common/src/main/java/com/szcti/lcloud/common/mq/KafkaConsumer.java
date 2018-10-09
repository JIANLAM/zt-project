package com.szcti.lcloud.common.mq;

import org.springframework.stereotype.Component;

/**
 * 消费者
 * 使用@KafkaListener注解,可以指定:主题,分区,消费组
 * 在上面的代码中，负责消费消息的关键之处就是SpringKafka提供的@KafkaListener注解，在方法上使用该注解，
 * 并指定要消费的topic（也可以指定消费组以及分区号，支持正则表达式匹配），这样，消费者一旦启动，就会监听kafka服务器上的topic，
 * 实时进行消费消息。当然，我们可以在该类中定义多个不同的方法，并都在方法上使用 @KafkaListener ，为它指定不同的topic及分区信息，这样每个方法就相当于一个消费者了。
 */
@Component
public class KafkaConsumer {

    //@KafkaListener(topics = {"app_log"})
    public void receive(String message){
        System.out.println("app_log--消费消息:" + message);
    }
}