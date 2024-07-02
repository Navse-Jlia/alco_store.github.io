package com.vasilkov.service.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vasilkov.model.dto.request.RabbitRequest;
import com.vasilkov.model.dto.request.RequestType;
import com.vasilkov.model.dto.response.RabbitResponse;
import com.vasilkov.service.service.AbstractService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.UUID;

/**
 * Abstract consumer for processing RabbitMQ messages.
 *
 * @param <S> the type of service
 * @param <D> the type of DTO
 * @author Artem Vasilkov
 */

@Getter
@Setter
@Log4j2
public abstract class AbstractConsumer<S extends AbstractService, D> {
    private S service;
    private ObjectMapper objectMapper;

    /**
     * Gets the class of DTO.
     *
     * @return the class of DTO
     */
    abstract Class<D> getClazz();

    /**
     * Gets the name of the queue.
     *
     * @return the name of the queue
     */
    protected abstract String getQueueName();

    /**
     * Creates a new instance of AbstractConsumer.
     *
     * @param service      the service
     * @param objectMapper the object mapper
     */
    public AbstractConsumer(S service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    /**
     * Handles the incoming RabbitMQ message.
     *
     * @param request the request message
     * @return the response message
     */
    protected String handleMessage(String request) {
        String message = null;
        String error = null;
        try {
            RabbitRequest rabbitRequest = objectMapper.readValue(request, RabbitRequest.class);
            log.info("Processing {} request with body {}", rabbitRequest.getType(), rabbitRequest.getMessage());
            switch (rabbitRequest.getType()) {
                case RequestType.GET_BY_ID:
                    UUID id = UUID.fromString(rabbitRequest.getMessage());
                    message = objectMapper.writeValueAsString(handleGetById(id));
                    break;

                case RequestType.GET_ALL:
                    message = objectMapper.writeValueAsString(handleGetAll());
                    break;

                case RequestType.SAVE:
                    D dto = objectMapper.readValue(rabbitRequest.getMessage(), getClazz());
                    handleSave(dto);
                    break;

                case RequestType.DELETE:
                    UUID uuid = UUID.fromString(rabbitRequest.getMessage());
                    handleDelete(uuid);
                    break;

                default:
                    message = listenCustom(rabbitRequest);
                    break;
            }

        } catch (Exception e) {
            log.error("Request failed", e);
            error = e.getMessage();
        }

        RabbitResponse response = RabbitResponse.builder()
                .message(message)
                .error(error)
                .build();
        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            return "{message:\"\", error:\"Ошибка JSON\"}";
        }
    }

    /**
     * Listens for custom message types.
     *
     * @param rabbitRequest the RabbitMQ request
     * @return the response message
     * @throws JsonProcessingException if an error occurs during JSON processing
     */
    protected abstract String listenCustom(RabbitRequest rabbitRequest) throws JsonProcessingException;

    /**
     * Handles the "get by id" request.
     *
     * @param id the ID of the entity
     * @return the DTO object
     */
    protected D handleGetById(UUID id) {
        return (D) service.getById(id);
    }

    /**
     * Handles the "get all" request.
     *
     * @return the list of DTO objects
     */
    protected List<D> handleGetAll() {
        return service.getAll();
    }

    /**
     * Handles the "save" request.
     *
     * @param dto the DTO object to save
     */
    protected void handleSave(D dto) {
        service.save(dto);
    }

    /**
     * Handles the "delete" request.
     *
     * @param id the ID of the entity to delete
     */
    protected void handleDelete(UUID id) {
        service.deleteById(id);
    }
}
