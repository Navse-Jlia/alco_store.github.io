package com.vasilkov.service.service;


import com.vasilkov.model.dto.OrderDto;
import com.vasilkov.model.dto.OrderProductDto;
import com.vasilkov.service.config.CachingConfiguration;
import com.vasilkov.service.mapper.OrderMapper;
import com.vasilkov.service.model.Order;
import com.vasilkov.service.repository.OrderRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The service for managing order entities.
 * This service extends {@link AbstractService} and provides methods to interact with the {@link Order} entities,
 * such as getting orders by user ID and managing order products.
 * It uses the {@link OrderRepository} for database operations, the {@link OrderMapper} for mapping between
 * {@link Order} entities and {@link OrderDto} DTOs, and the {@link OrderProductService} for managing order products.
 *
 * @author Artem Vasilkov
 * @see AbstractService
 * @see OrderRepository
 * @see OrderMapper
 * @see Order
 * @see OrderDto
 * @see OrderProductService
 */
@CacheConfig(cacheNames = "order_service")
@Service
public class OrderService extends AbstractService<OrderRepository, Order, OrderDto, OrderMapper> {

    private final OrderProductService orderProductService;

    /**
     * Instantiates a new Order service.
     *
     * @param repository          the order repository
     * @param mapper              the order mapper
     * @param orderProductService the order product service
     */
    public OrderService(OrderRepository repository, OrderMapper mapper, OrderProductService orderProductService) {
        super(repository, mapper);
        this.orderProductService = orderProductService;
    }

    /**
     * Gets orders by user ID.
     * Retrieves a list of orders associated with the specified user ID and populates each order DTO with its
     * corresponding
     * order products.
     *
     * @param id the user ID
     * @return the list of order DTOs
     */
    @Cacheable(cacheResolver = CachingConfiguration.CACHE_RESOLVER_NAME)
    public List<OrderDto> getOrdersByUserId(UUID id) {
        List<OrderProductDto> orderProductsDto = orderProductService.getAllByUserId(id);

        List<OrderDto> ordersDto = getMapper().toDtoList(getRepository().getOrdersByUserId(id));

        Map<UUID, List<OrderProductDto>> productsByOrder = orderProductsDto.stream()
                .collect(Collectors.groupingBy(OrderProductDto::getOrderId));

        for (OrderDto orderDto : ordersDto) {
            orderDto.setProducts(productsByOrder.get(orderDto.getId()));
        }
        return ordersDto;
    }
}
