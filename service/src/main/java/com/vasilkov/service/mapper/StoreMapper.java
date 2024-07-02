package com.vasilkov.service.mapper;

import com.vasilkov.model.dto.StoreDto;
import com.vasilkov.service.model.Store;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * The StoreMapper interface is responsible for mapping between the Store entity
 * and the StoreDto data transfer object (DTO).
 * It is annotated with @Mapper from the MapStruct library, indicating that it is a mapper interface.
 * The componentModel attribute is set to "spring", allowing Spring to manage instances of this mapper.
 * The injectionStrategy attribute is set to InjectionStrategy.CONSTRUCTOR, indicating constructor-based dependency
 * injection strategy.
 * This interface extends the CommonMapper interface, providing common mapping methods for entities and DTOs.
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StoreMapper extends CommonMapper<Store, StoreDto> {

}