package com.vasilkov.model.exception;

import com.vasilkov.model.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * The type AlcoStore exception.
 *
 * @author Artem Vasilkov
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AlcoStoreException extends RuntimeException {
    private Exception exception;
    private String message;
    private ErrorCode errorCode;
}
