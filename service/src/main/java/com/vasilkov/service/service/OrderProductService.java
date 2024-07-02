package com.vasilkov.service.service;

import com.vasilkov.model.dto.OrderProductDto;
import com.vasilkov.service.mapper.OrderProductMapper;
import com.vasilkov.service.model.OrderProduct;
import com.vasilkov.service.repository.OrderProductRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * The service for managing order product entities.
 * This service extends {@link AbstractService} and provides methods to interact with the {@link OrderProduct} entities,
 * such as getting all order products by user ID.
 * It uses the {@link OrderProductRepository} for database operations, the {@link OrderProductMapper} for mapping
 * between
 * {@link OrderProduct} entities and {@link OrderProductDto} DTOs.
 *
 * @author Artem Vasilkov
 * @see AbstractService
 * @see OrderProductRepository
 * @see OrderProductMapper
 * @see OrderProduct
 * @see OrderProductDto
 */
@CacheConfig(cacheNames = "order_product_service")
@Service
public class OrderProductService extends AbstractService<OrderProductRepository, OrderProduct, OrderProductDto,
        OrderProductMapper> {

    /**
     * Instantiates a new Order product service.
     *
     * @param repository the order product repository
     * @param mapper     the order product mapper
     */
    public OrderProductService(OrderProductRepository repository, OrderProductMapper mapper) {
        super(repository, mapper);
    }

    /**
     * Gets all order products by user ID.
     * Retrieves a list of order products associated with the specified user ID.
     *
     * @param id the user ID
     * @return the list of order product DTOs
     */
    @Cacheable
    public List<OrderProductDto> getAllByUserId(UUID id) {
        //TODO springdata jpa naming
        return getMapper().toDtoList(getRepository().findAllByOrder_UserId(id));
    }
}

