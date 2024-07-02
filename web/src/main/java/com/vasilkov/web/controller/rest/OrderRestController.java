package com.vasilkov.web.controller.rest;

import com.vasilkov.model.dto.OrderDto;
import com.vasilkov.web.rabbit.producer.OrderProducer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Order rest controller.
 *
 * @author Artem Vasilkov
 */
@RequestMapping("/api/orders")
@RestController
public class OrderRestController extends AbstractRestController<OrderDto, OrderProducer> {

}
