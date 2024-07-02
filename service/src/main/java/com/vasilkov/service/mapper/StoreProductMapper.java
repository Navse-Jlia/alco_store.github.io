package com.vasilkov.service.mapper;


import com.vasilkov.model.dto.StoreProductDto;
import com.vasilkov.service.model.StoreProduct;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;


/**
 * The StoreProductMapper interface is responsible for mapping between the StoreProduct entity
 * and the StoreProductDto data transfer object (DTO).
 * It is annotated with @Mapper from the MapStruct library, indicating that it is a mapper interface.
 * The componentModel attribute is set to "spring", allowing Spring to manage instances of this mapper.
 * The injectionStrategy attribute is set to InjectionStrategy.CONSTRUCTOR, indicating constructor-based dependency
 * injection strategy.
 * This interface extends the CommonMapper interface, providing common mapping methods for entities and DTOs.
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StoreProductMapper extends CommonMapper<StoreProduct, StoreProductDto> {

}