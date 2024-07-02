package com.vasilkov.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * The type Order dto.
 *
 * @author Artem Vasilkov
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID Id;

    @NotNull
    @NotEmpty
    private List<OrderProductDto> products;

    @NotNull
    @NotEmpty
    private UserDto user;

    @NotBlank
    private String address;
}
