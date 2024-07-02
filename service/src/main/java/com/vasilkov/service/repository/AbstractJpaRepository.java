package com.vasilkov.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface AbstractJpaRepository<E> extends JpaRepository<E, UUID>, JpaSpecificationExecutor<E> {
}
