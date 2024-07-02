package com.vasilkov.web.rabbit.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vasilkov.model.dto.OrderDto;
import com.vasilkov.web.rabbit.RabbitRoutingKey;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * The type Order producer.
 *
 * @author Artem Vasilkov
 */
@Service
public class OrderProducer extends AbstractRabbitMQProducer<OrderDto> {
    /**
     * Instantiates a new Order producer.
     *
     * @param rabbitTemplate the rabbit template
     * @param objectMapper   the object mapper
     */
    public OrderProducer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        super(rabbitTemplate, objectMapper);
    }

    /**
     * Routing key string.
     *
     * @return the string
     */
    @Override
    protected String routingKey() {
        return RabbitRoutingKey.ROUTING_KEY_ORDER;
    }

    /**
     * Gets clazz.
     *
     * @return the clazz
     */
    @Override
    protected Class getClazz() {
        return OrderDto.class;
    }
}
