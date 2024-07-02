package com.vasilkov.service.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vasilkov.model.dto.OrderDto;
import com.vasilkov.model.dto.request.RabbitRequest;
import com.vasilkov.service.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * Consumer for processing RabbitMQ messages related to order operations.
 */
@Log4j2
@Configuration
class OrderConsumer extends AbstractConsumer<OrderService, OrderDto> {

    /**
     * Instantiates a new OrderConsumer.
     *
     * @param service      the order service
     * @param objectMapper the object mapper
     */
    OrderConsumer(OrderService service, ObjectMapper objectMapper) {
        super(service, objectMapper);
    }

    @Override
    protected String listenCustom(RabbitRequest rabbitRequest) {
        return "";
    }

    @Override
    protected String getQueueName() {
        return RabbitQueue.ORDER_QUEUE;
    }

    @Override
    Class<OrderDto> getClazz() {
        return OrderDto.class;
    }

    /**
     * Handles incoming RabbitMQ messages for order operations.
     *
     * @param request the request message
     * @return the response message
     */
    @RabbitListener(queues = RabbitQueue.ORDER_QUEUE)
    private String useHandleMessage(String request) {
        return handleMessage(request);
    }
}
