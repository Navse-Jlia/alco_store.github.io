package com.vasilkov.service.mapper;

import java.util.List;

/**
 * This interface defines common mapping methods between entity objects (E) and DTO objects (D).
 * It provides methods for converting single objects as well as lists of objects.
 *
 * @param <E> the type of the entity object
 * @param <D> the type of the DTO object
 * @author Artem Vasilkov
 */
public interface CommonMapper<E, D> {

    /**
     * Converts an entity object to a DTO object.
     *
     * @param entity the entity object to convert
     * @return the DTO object
     */
    D toDto(E entity);

    /**
     * Converts a DTO object to an entity object.
     *
     * @param dto the DTO object to convert
     * @return the entity object
     */
    E toEntity(D dto);

    /**
     * Converts a list of entity objects to a list of DTO objects.
     *
     * @param entities the list of entity objects to convert
     * @return the list of DTO objects
     */
    List<D> toDtoList(List<E> entities);

    /**
     * Converts a list of DTO objects to a list of entity objects.
     *
     * @param dtos the list of DTO objects to convert
     * @return the list of entity objects
     */
    List<E> toEntityList(List<D> dtos);
}
