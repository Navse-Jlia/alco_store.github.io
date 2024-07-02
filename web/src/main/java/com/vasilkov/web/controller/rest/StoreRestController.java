package com.vasilkov.web.controller.rest;

import com.vasilkov.model.dto.StoreDto;
import com.vasilkov.web.rabbit.producer.StoreProducer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Store rest controller.
 *
 * @author Artem Vasilkov
 */
@RestController
@RequestMapping("/api/stores")
public class StoreRestController extends AbstractRestController<StoreDto, StoreProducer> {

}
