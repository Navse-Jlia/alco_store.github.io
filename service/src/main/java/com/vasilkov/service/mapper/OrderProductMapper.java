package com.vasilkov.service.mapper;

import com.vasilkov.model.dto.OrderProductDto;
import com.vasilkov.service.model.OrderProduct;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * This interface defines mapping methods between {@link OrderProduct} entities and {@link OrderProductDto} DTOs.
 * It extends {@link CommonMapper} to inherit common mapping methods.
 * The mapping is performed using MapStruct library with constructor injection strategy.
 *
 * @author Artem Vasilkov
 * @see CommonMapper
 * @see OrderProduct
 * @see OrderProductDto
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderProductMapper extends CommonMapper<OrderProduct, OrderProductDto> {
}

