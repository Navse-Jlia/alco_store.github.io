package com.vasilkov.service.repository;

import com.vasilkov.service.model.OrderProduct;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * This repository interface provides access to the OrderProduct entity in the database.
 * It extends the JpaRepository interface to inherit basic CRUD operations.
 *
 * @author Artem Vasilkov
 */
@Repository
public interface OrderProductRepository extends AbstractJpaRepository<OrderProduct> {

    /**
     * Retrieves all order products associated with a user ID.
     *
     * @param id the user ID to retrieve order products for
     * @return a list of order products associated with the given user ID
     */
    List<OrderProduct> findAllByOrder_UserId(UUID id);
}

