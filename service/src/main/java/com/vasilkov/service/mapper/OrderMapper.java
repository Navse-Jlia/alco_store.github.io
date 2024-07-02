package com.vasilkov.service.mapper;

import com.vasilkov.model.dto.OrderDto;
import com.vasilkov.service.model.Order;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * This interface defines mapping methods between {@link Order} entities and {@link OrderDto} DTOs.
 * It extends {@link CommonMapper} to inherit common mapping methods.
 * The mapping is performed using MapStruct library with Spring component model and constructor-based injection
 * strategy.
 *
 * @author Artem Vasilkov
 * @see CommonMapper
 * @see Order
 * @see OrderDto
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderMapper extends CommonMapper<Order, OrderDto> {

}
