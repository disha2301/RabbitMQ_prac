package com.gevernova.rabbitmq_practice.config;

// config package class to define different spring bean to configure RabbitMQ queue,
// exchange & binding between queue and exchange

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    public static final String QUEUE = "rabbitmq_queue";
    public static final String EXCHANGE = "rarbbitmq_exchange";
    public static final String ROUTING_KEY = "rabbitmq_routingkey";

    //spring bean for RabbitMQ queue
    // Declare a queue
    @Bean
    public Queue queue(){
        return new Queue(QUEUE);
    }

    // Declare a topic exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }

    // bind queue to the exhange using a routing key
    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
    }

    // define a message converter (JSON)
    @Bean
    public Jackson2JsonMessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    // Define a template (RabbitTemplate) to send/receive messages
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
