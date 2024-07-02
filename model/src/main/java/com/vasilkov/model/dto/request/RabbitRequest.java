package com.vasilkov.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Rabbit request.
 *
 * @author Artem Vasilkov
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Запрос в бд с методом и данными")
public class RabbitRequest {
    @Schema(description = "Метод запроса", example = "GET_BY_ID")
    private String type;

    @Schema(description = "Данные, которые передашь(если передаешь)", example = "{age:5, alias:Murka}")
    private String message;
}

