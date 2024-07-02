package com.vasilkov.service.service;


import com.vasilkov.model.dto.StoreDto;
import com.vasilkov.service.mapper.StoreMapper;
import com.vasilkov.service.model.Store;
import com.vasilkov.service.repository.StoreRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * The service for managing store entities.
 * This service extends {@link AbstractService} and provides methods to interact with the {@link Store} entities,
 * such as getting a store by ID, getting all stores, saving a store, and deleting a store.
 * It uses the {@link StoreRepository} for database operations and the {@link StoreMapper} for mapping between
 * {@link Store} entities and {@link StoreDto} DTOs.
 *
 * @author Artem Vasilkov
 * @see AbstractService
 * @see StoreRepository
 * @see StoreMapper
 * @see Store
 * @see StoreDto
 */
@CacheConfig(cacheNames = "store_service")
@Service
public class StoreService extends AbstractService<StoreRepository, Store, StoreDto, StoreMapper> {

    /**
     * Instantiates a new Store service.
     *
     * @param repository the store repository
     * @param mapper     the store mapper
     */
    public StoreService(StoreRepository repository, StoreMapper mapper) {
        super(repository, mapper);
    }
}
