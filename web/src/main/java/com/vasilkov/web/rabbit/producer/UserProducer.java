package com.vasilkov.web.rabbit.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vasilkov.model.dto.UserDto;
import com.vasilkov.model.dto.request.RequestType;
import com.vasilkov.model.enums.ErrorCode;
import com.vasilkov.model.exception.AlcoStoreException;
import com.vasilkov.web.rabbit.RabbitRoutingKey;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * The type User producer.
 *
 * @author Artem Vasilkov
 */
@Service
public class UserProducer extends AbstractRabbitMQProducer<UserDto> {
    /**
     * Instantiates a new User producer.
     *
     * @param rabbitTemplate the rabbit template
     * @param objectMapper   the object mapper
     */
    public UserProducer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        super(rabbitTemplate, objectMapper);
    }

    /**
     * Routing key string.
     *
     * @return the string
     */
    @Override
    protected String routingKey() {
        return RabbitRoutingKey.ROUTING_KEY_USER;
    }

    /**
     * Gets clazz.
     *
     * @return the clazz
     */
    @Override
    protected Class getClazz() {
        return UserDto.class;
    }

    /**
     * Gets by email.
     *
     * @param email the email
     * @return the by email
     */
    public UserDto getByEmail(String email) {
        try {
            return getObjectMapper().readValue(sendAndReceive(RequestType.GET_BY_EMAIL, email), UserDto.class);
        } catch (JsonProcessingException e) {
            throw new AlcoStoreException(e, e.getMessage(), ErrorCode.JSON_PARSING);
        }
    }
}
