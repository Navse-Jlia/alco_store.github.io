package com.vasilkov.service.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vasilkov.model.dto.StoreDto;
import com.vasilkov.model.dto.request.RabbitRequest;
import com.vasilkov.service.service.StoreService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * The type Store consumer.
 *
 * @author Artem Vasilkov
 */
@Log4j2
@Configuration
public class StoreConsumer extends AbstractConsumer<StoreService, StoreDto> {

    /**
     * Instantiates a new Store consumer.
     *
     * @param service      store service
     * @param objectMapper object for transform other objects
     */
    public StoreConsumer(StoreService service, ObjectMapper objectMapper) {
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
        return RabbitQueue.STORE_QUEUE;
    }

    /**
     * Setting Dto.class for deserialization in readValue() method
     *
     * @return
     */
    @Override
    Class<StoreDto> getClazz() {
        return StoreDto.class;
    }

    /**
     * Setting queue name and call <code>handleMessage()</code>, that deserialize message from RabbitRequest and
     * build answer RabbitResponse from service to web.
     *
     * @param request request from web microservice with RequestType and message(null, id or dto)
     * @return response message in JSON string.
     */
    @RabbitListener(queues = RabbitQueue.STORE_QUEUE)
    private String useHandleMessage(String request) {
        return handleMessage(request);
    }
}
