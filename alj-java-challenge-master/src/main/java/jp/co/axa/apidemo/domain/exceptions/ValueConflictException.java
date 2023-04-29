package jp.co.axa.apidemo.domain.exceptions;

import org.springframework.lang.NonNull;

/**
 * Throw the error when values has conflict with each other
 */
public class ValueConflictException extends RuntimeException{

    public ValueConflictException(
            @NonNull final String message
    ) {
        super(message);
    }
}
