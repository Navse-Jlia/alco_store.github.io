package com.vasilkov.web.rabbit.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vasilkov.model.dto.StoreDto;
import com.vasilkov.web.rabbit.RabbitRoutingKey;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * The type Store producer.
 *
 * @author Artem Vasilkov
 */
@Service
public class StoreProducer extends AbstractRabbitMQProducer<StoreDto> {
    /**
     * Instantiates a new Store producer.
     *
     * @param rabbitTemplate the rabbit template
     * @param objectMapper   the object mapper
     */
    public StoreProducer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        super(rabbitTemplate, objectMapper);
    }

    /**
     * Routing key string.
     *
     * @return the string
     */
    @Override
    protected String routingKey() {
        return RabbitRoutingKey.ROUTING_KEY_STORE;
    }

    /**
     * Gets clazz.
     *
     * @return the clazz
     */
    @Override
    protected Class getClazz() {
        return StoreDto.class;
    }
}
