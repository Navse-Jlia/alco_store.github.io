package com.vasilkov.service.service;

import com.vasilkov.model.dto.UserDto;
import com.vasilkov.service.config.CachingConfiguration;
import com.vasilkov.service.mapper.UserMapper;
import com.vasilkov.service.model.User;
import com.vasilkov.service.repository.UserRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Service for user-related operations.
 */
@CacheConfig(cacheNames = "user_service")
@Service
public class UserService extends AbstractService<UserRepository, User, UserDto, UserMapper> {

    /**
     * Instantiates a new UserService.
     *
     * @param repository the user repository
     * @param mapper     the user mapper
     */
    public UserService(UserRepository repository, UserMapper mapper) {
        super(repository, mapper);
    }

    /**
     * Finds a user by email.
     *
     * @param email the email of the user
     * @return the user DTO object
     */
    @Cacheable(cacheResolver = CachingConfiguration.CACHE_RESOLVER_NAME)
    public UserDto findByEmail(String email) {
        return getMapper().toDto(getRepository().findByEmail(email));
    }

}
