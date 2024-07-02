package com.vasilkov.service.repository;

import com.vasilkov.service.model.Store;
import org.springframework.stereotype.Repository;

/**
 * The StoreRepository interface provides CRUD operations for the Store entity.
 * It extends the JpaRepository interface, which provides generic CRUD operations for entities.
 * The Store entity is associated with a UUID identifier.
 * This interface is annotated with @Repository, indicating that it is a Spring repository component.
 */
@Repository
public interface StoreRepository extends AbstractJpaRepository<Store> {
}