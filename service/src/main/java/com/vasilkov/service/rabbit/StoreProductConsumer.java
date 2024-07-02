package com.vasilkov.service.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vasilkov.model.dto.StoreProductDto;
import com.vasilkov.model.dto.request.RabbitRequest;
import com.vasilkov.service.service.StoreProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * The type Store product consumer.
 *
 * @author Artem Vasilkov
 */
@Log4j2
@Configuration
public class StoreProductConsumer extends AbstractConsumer<StoreProductService, StoreProductDto> {

    /**
     * Instantiates a new Store product consumer.
     *
     * @param service      store product service
     * @param objectMapper object for transform other objects
     */
    public StoreProductConsumer(StoreProductService service, ObjectMapper objectMapper) {
        super(service, objectMapper);
    }

    /**
     * Add custom RequestType message handler.
     *
     * @param rabbitRequest request with RequestType and message(null, id or dto)
     * @return nothing
     */
    @Override
    protected String listenCustom(RabbitRequest rabbitRequest) {
        return "";
    }

    /**
     * Setting the queue name value.
     *
     * @return
     */
    @Override
    protected String getQueueName() {
        return RabbitQueue.STORE_PRODUCT_QUEUE;
    }

    /**
     * Setting Dto.class for deserialization in readValue() method
     *
     * @return
     */
    @Override
    Class<StoreProductDto> getClazz() {
        return StoreProductDto.class;
    }

    /**
     * Setting queue name and call <code>handleMessage()</code>, that deserialize message from RabbitRequest and
     * build answer RabbitResponse from service to web.
     *
     * @param request request from web microservice with RequestType and message(null, id or dto)
     * @return response message in JSON string.
     */
    @RabbitListener(queues = RabbitQueue.STORE_PRODUCT_QUEUE)
    private String useHandleMessage(String request) {
        return handleMessage(request);
    }
}