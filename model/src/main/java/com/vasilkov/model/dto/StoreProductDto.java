package com.vasilkov.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * The type Store product dto.
 *
 * @author Artem Vasilkov
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreProductDto implements Serializable {
    private static final long serialVersionUID = 4L;

    private UUID id;
    @NotBlank
    private String title;

    private String description;

    @NotNull
    private Integer price;

    @NotNull
    private String seller;

    private String image;

    @NotNull
    private Integer quantity;

}
