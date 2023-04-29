package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.controllers.dto.error.ErrorResponse;
import jp.co.axa.apidemo.domain.exceptions.DataInsertionException;
import jp.co.axa.apidemo.domain.exceptions.ResourceNotFoundException;
import jp.co.axa.apidemo.domain.exceptions.ValueConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ApiExceptionHandler {

    /**
     * 400 - value conflict
     * @param ex ValueConflictException
     * @return
     */
    @ExceptionHandler({ValueConflictException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ValueConflictException ex) {

        return createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    /**
     * 400 - invalid argument
     * @param ex MethodArgumentNotValidException
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            MethodArgumentNotValidException ex) {

        return createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    /**
     * 404 - wrong url
     * @param ex NoHandlerFoundException
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException ex) {

        return createErrorResponse(
                HttpStatus.NOT_FOUND,
                "Path does not exists.");
    }

    /**
     * 404 - resource not found
     * @param ex ResourceNotFoundException
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex) {

        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    /**
     * 409 - data already exists
     * @param ex DataInsertionException
     * @return
     */
    @ExceptionHandler(DataInsertionException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(DataInsertionException ex) {

        return createErrorResponse(
                HttpStatus.CONFLICT, ex.getMessage());
    }

    /**
     * Create response entity
     * @param status http status
     * @param message error message
     * @return ResponseEntity
     */
    private ResponseEntity<ErrorResponse> createErrorResponse(
            final HttpStatus status,
            final String message
    ) {
        return ResponseEntity.status(status).body(new ErrorResponse(message));
    }

}
