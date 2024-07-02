package com.vasilkov.service.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vasilkov.model.dto.OrderProductDto;
import com.vasilkov.model.dto.request.RabbitRequest;
import com.vasilkov.service.service.OrderProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * Consumer for processing RabbitMQ messages related to order product operations.
 *
 * @author Artem Vasilkov
 */
@Log4j2
@Configuration
public class OrderProductConsumer extends AbstractConsumer<OrderProductService, OrderProductDto> {

    /**
     * Instantiates a new OrderProductConsumer.
     *
     * @param service      the order product service
     * @param objectMapper the object mapper
     */
    public OrderProductConsumer(OrderProductService service, ObjectMapper objectMapper) {
        super(service, objectMapper);
    }

    @Override
    protected String listenCustom(RabbitRequest rabbitRequest) {
        return "";
    }

    @Override
    protected String getQueueName() {
        return RabbitQueue.ORDER_PRODUCT_QUEUE;
    }

    @Override
    Class<OrderProductDto> getClazz() {
        return OrderProductDto.class;
    }

    /**
     * Handles incoming RabbitMQ messages for order product operations.
     *
     * @param request the request message
     * @return the response message
     */
    @RabbitListener(queues = RabbitQueue.ORDER_PRODUCT_QUEUE)
    private String useHandleMessage(String request) {
        return handleMessage(request);
    }
}
