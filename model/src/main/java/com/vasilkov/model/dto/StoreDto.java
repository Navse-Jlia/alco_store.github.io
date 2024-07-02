package com.vasilkov.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * The type Store dto.
 *
 * @author Artem Vasilkov
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto implements Serializable {
    private static final long serialVersionUID = 3L;

    private UUID id;
    @NotBlank
    private String nameStore;
    @NotBlank
    private String address;
    @NotBlank
    private String phoneNumber;
    private String description;

    private UserDto seller;

    private List<StoreProductDto> products;
}
