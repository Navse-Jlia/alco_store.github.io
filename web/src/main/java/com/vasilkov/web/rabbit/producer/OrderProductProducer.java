package com.vasilkov.web.rabbit.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vasilkov.model.dto.OrderProductDto;
import com.vasilkov.web.rabbit.RabbitRoutingKey;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * The type Order product producer.
 *
 * @author Artem Vasilkov
 */
@Service
public class OrderProductProducer extends AbstractRabbitMQProducer<OrderProductDto> {
    /**
     * Instantiates a new Order product producer.
     *
     * @param rabbitTemplate the rabbit template
     * @param objectMapper   the object mapper
     */
    public OrderProductProducer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        super(rabbitTemplate, objectMapper);
    }

    /**
     * Routing key string.
     *
     * @return the string
     */
    @Override
    protected String routingKey() {
        return RabbitRoutingKey.ROUTING_KEY_ORDER_PRODUCT;
    }

    /**
     * Gets clazz.
     *
     * @return the clazz
     */
    @Override
    protected Class getClazz() {
        return OrderProductDto.class;
    }
}
