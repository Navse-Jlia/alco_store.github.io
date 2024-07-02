package com.vasilkov.service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.UUID;

/**
 * Entity class representing an order product.
 *
 * @author Artem Vasilkov
 */
@Getter
@Setter
@Entity
@Audited
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer price;

    //TODO в сервисе запросить лист ордер продукт
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String image;

    private Integer quantity;

}
