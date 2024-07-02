package com.vasilkov.service.config;


import com.vasilkov.service.rabbit.RabbitExchange;
import com.vasilkov.service.rabbit.RabbitQueue;
import com.vasilkov.service.rabbit.RabbitRoutingKey;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for RabbitMQ setup.
 *
 * @author Artem Vasilkov
 */
@EnableRabbit
@Configuration
public class RabbitMQConfiguration {

    /**
     * Defines order queue.
     *
     * @return the queue
     */
    @Bean
    Queue orderQueue() {
        return new Queue(RabbitQueue.ORDER_QUEUE, false);
    }

    /**
     * Defines order product queue.
     *
     * @return the queue
     */
    @Bean
    Queue orderProductQueue() {
        return new Queue(RabbitQueue.ORDER_PRODUCT_QUEUE, false);
    }

    /**
     * Defines store queue.
     *
     * @return the queue
     */
    @Bean
    Queue storeQueue() {
        return new Queue(RabbitQueue.STORE_QUEUE, false);
    }

    /**
     * Defines store product queue.
     *
     * @return the queue
     */
    @Bean
    Queue storeProductQueue() {
        return new Queue(RabbitQueue.STORE_PRODUCT_QUEUE, false);
    }

    /**
     * Defines user queue.
     *
     * @return the queue
     */
    @Bean
    Queue userQueue() {
        return new Queue(RabbitQueue.USER_QUEUE, false);
    }

    /**
     * Defines the direct exchange.
     *
     * @return the direct exchange
     */
    @Bean
    DirectExchange exchange() {
        return new DirectExchange(RabbitExchange.ALCOSTORE_EXCHANGE);
    }

    /**
     * Defines the binding for the order queue.
     *
     * @param orderQueue the order queue
     * @param exchange   the exchange
     * @return the binding
     */
    @Bean
    Binding orderBinding(Queue orderQueue, DirectExchange exchange) {
        return BindingBuilder.bind(orderQueue).to(exchange).with(RabbitRoutingKey.ROUTING_KEY_ORDER);
    }

    /**
     * Defines the binding for the order product queue.
     *
     * @param orderProductQueue the order product queue
     * @param exchange          the exchange
     * @return the binding
     */
    @Bean
    Binding orderProductBinding(Queue orderProductQueue, DirectExchange exchange) {
        return BindingBuilder.bind(orderProductQueue).to(exchange).with(RabbitRoutingKey.ROUTING_KEY_ORDER_PRODUCT);
    }

    /**
     * Defines the binding for the store queue.
     *
     * @param storeQueue the store queue
     * @param exchange   the exchange
     * @return the binding
     */
    @Bean
    Binding storeBinding(Queue storeQueue, DirectExchange exchange) {
        return BindingBuilder.bind(storeQueue).to(exchange).with(RabbitRoutingKey.ROUTING_KEY_STORE);
    }

    /**
     * Defines the binding for the store product queue.
     *
     * @param storeProductQueue the store product queue
     * @param exchange          the exchange
     * @return the binding
     */
    @Bean
    Binding storeProductBinding(Queue storeProductQueue, DirectExchange exchange) {
        return BindingBuilder.bind(storeProductQueue).to(exchange).with(RabbitRoutingKey.ROUTING_KEY_STORE_PRODUCT);
    }

    /**
     * Defines the binding for the user queue.
     *
     * @param userQueue the user queue
     * @param exchange  the exchange
     * @return the binding
     */
    @Bean
    Binding userBinding(Queue userQueue, DirectExchange exchange) {
        return BindingBuilder.bind(userQueue).to(exchange).with(RabbitRoutingKey.ROUTING_KEY_USER);
    }

}
