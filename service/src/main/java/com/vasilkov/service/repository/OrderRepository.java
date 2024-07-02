package com.vasilkov.service.repository;

import com.vasilkov.service.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * This repository interface provides access to the Order entity in the database.
 * It extends the JpaRepository interface to inherit basic CRUD operations.
 */
@Repository
public interface OrderRepository extends AbstractJpaRepository<Order> {

    /**
     * Retrieves all orders associated with a user ID.
     *
     * @param id the user ID to retrieve orders for
     * @return a list of orders associated with the given user ID
     */
    List<Order> getOrdersByUserId(UUID id);
}

