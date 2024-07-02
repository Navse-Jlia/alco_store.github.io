package com.vasilkov.web.rabbit.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vasilkov.model.dto.request.RabbitRequest;
import com.vasilkov.model.dto.request.RequestType;
import com.vasilkov.model.dto.response.RabbitResponse;
import com.vasilkov.model.enums.ErrorCode;
import com.vasilkov.model.exception.AlcoStoreException;
import com.vasilkov.web.rabbit.RabbitExchange;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;
import java.util.UUID;

/**
 * The type Abstract rabbit mq producer.
 * * @param <D> the type parameter *
 *
 * @author Artem Vasilkov
 */
@Getter
@AllArgsConstructor
@Log4j2
public abstract class AbstractRabbitMQProducer<D> {
    /**
     * The Rabbit template.
     */
    private RabbitTemplate rabbitTemplate;
    /**
     * The Object mapper.
     */
    private ObjectMapper objectMapper;

    /**
     * Routing key string.
     *
     * @return the string
     */
    protected abstract String routingKey();

    /**
     * Gets clazz.
     *
     * @return the clazz
     */
    protected abstract Class getClazz();

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<D> getAll() {
        try {
            return objectMapper.readValue(sendAndReceive(RequestType.GET_ALL, null),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, getClazz()));
        } catch (JsonProcessingException e) {
            throw new AlcoStoreException(e, e.getMessage(), ErrorCode.JSON_PARSING);
        }
    }


    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public D getById(UUID id) {
        try {
            return (D) objectMapper.readValue(sendAndReceive(RequestType.GET_BY_ID, id), getClazz());
        } catch (JsonProcessingException e) {
            throw new AlcoStoreException(e, e.getMessage(), ErrorCode.JSON_PARSING);
        }
    }

    /**
     * Save.
     *
     * @param dto the dto
     */
    public void save(D dto) {
        try {
            sendAndReceive(RequestType.SAVE, dto);
        } catch (JsonProcessingException e) {
            throw new AlcoStoreException(e, e.getMessage(), ErrorCode.JSON_PARSING);
        }
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(UUID id) {
        try {
            sendAndReceive(RequestType.DELETE, id);
        } catch (JsonProcessingException e) {
            throw new AlcoStoreException(e, e.getMessage(), ErrorCode.JSON_PARSING);
        }
    }

    /**
     * Send and receive string.
     *
     * @param requestType the request type
     * @param message     the message
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    protected String sendAndReceive(String requestType, Object message) throws JsonProcessingException {
        RabbitRequest request = null;
        if (message instanceof String) {
            request = new RabbitRequest(requestType, message.toString());
        } else {
            request = new RabbitRequest(requestType, objectMapper.writeValueAsString(message));
        }
        String requestToString = objectMapper.writeValueAsString(request);
        String response = (String) rabbitTemplate.convertSendAndReceive(RabbitExchange.ALCOSTORE_EXCHANGE,
                routingKey(), requestToString);
        log.info("RabbitResponse on {} to queue {}: {}", requestType, routingKey(), response);
        RabbitResponse result = objectMapper.readValue(response, RabbitResponse.class);
        if (result.getError() != null) {
            throw new AlcoStoreException(null, result.getError(), ErrorCode.INTERNAL_ERROR);
        }
        return result.getMessage();
    }


}
