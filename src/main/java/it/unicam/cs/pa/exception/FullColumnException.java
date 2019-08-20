package it.unicam.cs.pa.exception;

/**
 * Throw when full column is reached
 */

public class FullColumnException extends RuntimeException {
    public FullColumnException(String message) {
        super(message);
    }
}
