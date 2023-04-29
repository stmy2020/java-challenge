package jp.co.axa.apidemo.domain.exceptions;

import org.springframework.lang.NonNull;

/**
 * Throw the error when resources are not found
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(
            @NonNull final String message
    ) {
        super(message);
    }
}
