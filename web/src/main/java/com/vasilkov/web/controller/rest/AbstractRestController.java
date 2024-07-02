package com.vasilkov.web.controller.rest;


import com.vasilkov.web.rabbit.producer.AbstractRabbitMQProducer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

/**
 * The type Abstract rest controller.
 * * @param <D> the type parameter * @param <P> the type parameter *
 *
 * @author Artem Vasilkov
 */
@Getter
@Setter
@RequiredArgsConstructor
public abstract class AbstractRestController<D, P extends AbstractRabbitMQProducer<D>> {

    /**
     * The Producer.
     */
    private P producer;

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping
    public ResponseEntity<List<D>> getAll() {
        List<D> dto = producer.getAll();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<D> getById(@PathVariable UUID id) {
        D dto = producer.getById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * Save response entity.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<Void> save(@Validated @RequestBody D dto) {
        producer.save(dto);
        return ResponseEntity.ok().build();
    }

    /**
     * Update response entity.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PutMapping
    public ResponseEntity<D> update(@Validated @RequestBody D dto) {
        producer.save(dto);
        return ResponseEntity.ok().build();
    }

    /**
     * Delete response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        producer.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
