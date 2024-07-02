package com.vasilkov.service.repository;

import com.vasilkov.service.model.StoreProduct;
import org.springframework.stereotype.Repository;

/**
 * The StoreProductRepository interface provides CRUD operations for the StoreProduct entity.
 * It extends the JpaRepository interface, which provides generic CRUD operations for entities.
 * The StoreProduct entity is associated with a UUID identifier.
 * This interface is annotated with @Repository, indicating that it is a Spring repository component.
 */
@Repository
public interface StoreProductRepository extends AbstractJpaRepository<StoreProduct> {

    /**
     * Retrieves a store product by its title.
     *
     * @param title the title of the store product
     * @return the store product with the specified title, or null if not found
     */
    StoreProduct findByTitle(String title);
}
