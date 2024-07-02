package com.vasilkov.service.mapper;

import com.vasilkov.model.dto.UserDto;
import com.vasilkov.service.model.User;
import org.mapstruct.Mapper;

/**
 * This interface defines mapping methods between {@link User} entities and {@link UserDto} DTOs.
 * It extends {@link CommonMapper} to inherit common mapping methods.
 * The mapping is performed using MapStruct library with Spring component model.
 *
 * @author Artem Vasilkov
 * @see CommonMapper
 * @see User
 * @see UserDto
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends CommonMapper<User, UserDto> {
}
