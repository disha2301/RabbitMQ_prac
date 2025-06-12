package com.gevernova.rabbitmq_practice.producer;

import com.gevernova.rabbitmq_practice.config.MessagingConfig;
import com.gevernova.rabbitmq_practice.entity.Order;
import com.gevernova.rabbitmq_practice.entity.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController // @Controller + @ResponseBody
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderPublisher {
    public final RabbitTemplate rabbitTemplate;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName ){
        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed successfully in "+restaurantName);
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderStatus);
        return "Success";
    }
}
