package com.vasilkov.service.repository;

import com.vasilkov.service.model.User;
import org.springframework.stereotype.Repository;

/**
 * The UserRepository interface provides CRUD operations for the User entity.
 * It extends the JpaRepository interface, which provides generic CRUD operations for entities.
 * The User entity is associated with a UUID identifier.
 * This interface is annotated with @Repository, indicating that it is a Spring repository component.
 */
@Repository
public interface UserRepository extends AbstractJpaRepository<User> {

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email address of the user
     * @return the user with the specified email address, or null if not found
     */
    User findByEmail(String email);

    /**
     * Checks if a user exists with the specified email address.
     *
     * @param email the email address to check
     * @return true if a user with the email address exists, otherwise false
     */
    boolean existsByEmail(String email);

}
