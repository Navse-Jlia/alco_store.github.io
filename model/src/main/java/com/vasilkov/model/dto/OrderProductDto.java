package com.vasilkov.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * The type Order product dto.
 *
 * @author Artem Vasilkov
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDto implements Serializable {
    private static final long serialVersionUID = 2L;

    private UUID id;

    @NotBlank
    private String title;

    private String description;

    private UUID orderId;

    @NotBlank
    private Integer price;

    private String city;

    private String image;
    @NotNull
    private Integer quantity;
}
