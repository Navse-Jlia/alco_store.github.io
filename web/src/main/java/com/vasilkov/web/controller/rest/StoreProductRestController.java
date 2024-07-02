package com.vasilkov.web.controller.rest;

import com.vasilkov.model.dto.StoreProductDto;
import com.vasilkov.web.rabbit.producer.StoreProductProducer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Store product rest controller.
 *
 * @author Artem Vasilkov
 */
@RequestMapping("/api/store-products")
@RestController
public class StoreProductRestController extends AbstractRestController<StoreProductDto, StoreProductProducer> {

}