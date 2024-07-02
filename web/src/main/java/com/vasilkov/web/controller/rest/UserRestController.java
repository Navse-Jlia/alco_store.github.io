package com.vasilkov.web.controller.rest;

import com.vasilkov.model.dto.UserDto;
import com.vasilkov.web.rabbit.producer.UserProducer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type User rest controller.
 *
 * @author Artem Vasilkov
 */
@RestController
@RequestMapping("/api/users")
public class UserRestController extends AbstractRestController<UserDto, UserProducer> {

}
