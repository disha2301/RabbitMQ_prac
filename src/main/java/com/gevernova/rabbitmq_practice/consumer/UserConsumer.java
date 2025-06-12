package com.gevernova.rabbitmq_practice.consumer;

import com.gevernova.rabbitmq_practice.config.MessagingConfig;
import com.gevernova.rabbitmq_practice.entity.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserConsumer {
    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus){
        log.info("Message received from queue: " + orderStatus);
    }
}
