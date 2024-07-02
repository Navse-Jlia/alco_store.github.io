package com.vasilkov.web.controller.rest;

import com.vasilkov.model.dto.OrderProductDto;
import com.vasilkov.web.rabbit.producer.OrderProductProducer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Order product rest controller.
 *
 * @author Artem Vasilkov
 */
@RestController
@RequestMapping("/api/order-products")
public class OrderProductRestController extends AbstractRestController<OrderProductDto, OrderProductProducer> {

}
