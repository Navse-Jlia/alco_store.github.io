package com.vasilkov.service.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vasilkov.model.dto.UserDto;
import com.vasilkov.model.dto.request.RabbitRequest;
import com.vasilkov.model.dto.request.RequestType;
import com.vasilkov.model.enums.ErrorCode;
import com.vasilkov.model.exception.AlcoStoreException;
import com.vasilkov.service.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * Consumer for processing RabbitMQ messages related to user operations.
 */
@Log4j2
@Configuration
public class UserConsumer extends AbstractConsumer<UserService, UserDto> {

    /**
     * Instantiates a new UserConsumer.
     *
     * @param service      the user service
     * @param objectMapper the object mapper
     */
    public UserConsumer(UserService service, ObjectMapper objectMapper) {
        super(service, objectMapper);
    }

    @Override
    protected String listenCustom(RabbitRequest rabbitRequest) throws JsonProcessingException {
        switch (rabbitRequest.getType()) {
            case RequestType.GET_BY_EMAIL:
                return getObjectMapper().writeValueAsString(handleFindByEmail(rabbitRequest.getMessage()));
            default:
                throw new AlcoStoreException(null, "Invalid operation type.", ErrorCode.REQUEST_ERROR);
        }
    }

    @Override
    protected String getQueueName() {
        return RabbitQueue.USER_QUEUE;
    }

    @Override
    Class<UserDto> getClazz() {
        return UserDto.class;
    }

    /**
     * Handles incoming RabbitMQ messages for user operations.
     *
     * @param request the request message
     * @return the response message
     */
    @RabbitListener(queues = RabbitQueue.USER_QUEUE)
    public String useHandeMessage(String request) {
        return handleMessage(request);
    }

    /**
     * Handles finding user by email.
     *
     * @param email the email of the user
     * @return the user DTO object
     */
    private UserDto handleFindByEmail(String email) {
        return getService().findByEmail(email);
    }
}
