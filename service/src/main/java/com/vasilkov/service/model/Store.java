package com.vasilkov.service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.UUID;

/**
 * Entity class representing a Store.
 *
 * @author Artem Vasilkov
 */
@Getter
@Setter
@Entity
@Audited
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nameStore;

    @NonNull
    @Column(nullable = false)
    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne
    private User seller;

}
