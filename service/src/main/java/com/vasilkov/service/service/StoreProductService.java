package com.vasilkov.service.service;

import com.vasilkov.model.dto.StoreProductDto;
import com.vasilkov.service.config.CachingConfiguration;
import com.vasilkov.service.mapper.StoreProductMapper;
import com.vasilkov.service.model.StoreProduct;
import com.vasilkov.service.repository.StoreProductRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * Service for store product-related operations.
 */
@CacheConfig(cacheNames = "store_product_service")
@Service
public class StoreProductService extends AbstractService<StoreProductRepository, StoreProduct, StoreProductDto,
        StoreProductMapper> {

    /**
     * Instantiates a new StoreProductService.
     *
     * @param repository the store product repository
     * @param mapper     the store product mapper
     */
    public StoreProductService(StoreProductRepository repository, StoreProductMapper mapper) {
        super(repository, mapper);
    }

    @Cacheable(cacheResolver = CachingConfiguration.CACHE_RESOLVER_NAME)
    public List<StoreProductDto> getProductsAlphabetically() {
        List<StoreProductDto> products = getMapper().toDtoList(getRepository().findAll());
        products.sort(Comparator.comparing(StoreProductDto::getTitle));
        return products;
    }
}
