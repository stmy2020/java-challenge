package jp.co.axa.apidemo.domain.exceptions;

import org.springframework.lang.NonNull;

/**
 * Throw the error when try to add existing data to the database
 */
public class DataInsertionException extends RuntimeException {

    public DataInsertionException(
            @NonNull final String message
    ) {
        super(message);
    }
}
