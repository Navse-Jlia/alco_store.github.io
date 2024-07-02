package com.vasilkov.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Rabbit response.
 *
 * @author Artem Vasilkov
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Ответ в бд с методом и данными")
public class RabbitResponse {
    @Schema(description = "Тело ответа")
    private String message;
    @Schema(description = "Статус")
    private String error;
}
