package com.vasilkov.service.service;

import com.vasilkov.service.config.CachingConfiguration;
import com.vasilkov.service.mapper.CommonMapper;
import com.vasilkov.service.repository.AbstractJpaRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

/**
 * Abstract service providing common CRUD operations.
 *
 * @param <R> the type of repository
 * @param <E> the type of entity
 * @param <D> the type of DTO
 * @param <M> the type of mapper
 */
@Setter
@Getter
@CacheConfig(cacheNames = "default_cache")
public abstract class AbstractService<R extends AbstractJpaRepository<E>, E, D, M extends CommonMapper<E, D>> {

    private R repository;
    private M mapper;

    /**
     * Instantiates a new AbstractService.
     *
     * @param repository the repository
     * @param mapper     the mapper
     */
    public AbstractService(R repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Gets entity by id.
     *
     * @param id the id
     * @return the DTO object
     */
    @Cacheable(cacheResolver = CachingConfiguration.CACHE_RESOLVER_NAME)
    public D getById(UUID id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }

    /**
     * Gets all entities.
     *
     * @return the list of DTO objects
     */
    @Cacheable(cacheResolver = CachingConfiguration.CACHE_RESOLVER_NAME)
    public List<D> getAll() {
        return mapper.toDtoList(repository.findAll());
    }

    /**
     * Saves entity.
     *
     * @param dto the DTO object
     * @return the saved DTO object
     */
    @CacheEvict(allEntries = true)
    @Transactional
    public D save(D dto) {
        E entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    /**
     * Deletes entity by id.
     *
     * @param id the id
     */
    @CacheEvict(allEntries = true)
    @Transactional
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Cacheable(cacheResolver = CachingConfiguration.CACHE_RESOLVER_NAME)
    public List<D> filter(String title) {
        Specification<E> specification = (root, query, cb) -> cb.like(root.get("title"), title);

        return mapper.toDtoList(repository.findAll(Sort.by(Sort.Direction.DESC, "title")));
    }
}
