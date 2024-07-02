package com.vasilkov.web.rabbit.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vasilkov.model.dto.StoreProductDto;
import com.vasilkov.web.rabbit.RabbitRoutingKey;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * The type Store product producer.
 *
 * @author Artem Vasilkov
 */
@Service
public class StoreProductProducer extends AbstractRabbitMQProducer<StoreProductDto> {

    /**
     * Instantiates a new Store product producer.
     *
     * @param rabbitTemplate the rabbit template
     * @param objectMapper   the object mapper
     */
    public StoreProductProducer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        super(rabbitTemplate, objectMapper);
    }

    /**
     * Routing key string.
     *
     * @return the string
     */
    @Override
    protected String routingKey() {
        return RabbitRoutingKey.ROUTING_KEY_STORE_PRODUCT;
    }

    /**
     * Gets clazz.
     *
     * @return the clazz
     */
    @Override
    protected Class getClazz() {
        return StoreProductDto.class;
    }
}
