package com.vasilkov.web.handler;

import com.vasilkov.model.enums.ErrorCode;
import com.vasilkov.model.exception.AlcoStoreException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type Alco store exception handler.
 *
 * @author Artem Vasilkov
 */
@Log4j2
@ControllerAdvice
public class AlcoStoreExceptionHandler {

    /**
     * Handle default exception error response.
     *
     * @param e the e
     * @return the error response
     */
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleDefaultException(Exception e) {
        log.error(e.getMessage(), e);
        return ErrorResponse.create(e, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    /**
     * Handle alco store exception error response.
     *
     * @param e the e
     * @return the error response
     */
    @ExceptionHandler(AlcoStoreException.class)
    public ErrorResponse handleAlcoStoreException(AlcoStoreException e) {
        log.error(e.getMessage(), e.getException() == null ? e : e.getException());
        HttpStatus httpStatus = ErrorCode.NOT_FOUND.equals(e.getErrorCode()) ? HttpStatus.NOT_FOUND
                : HttpStatus.INTERNAL_SERVER_ERROR;
        return ErrorResponse.create(e, httpStatus, e.getMessage());
    }
}
