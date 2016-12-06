package com.unidev.platform.common.exception;

/**
 * Unidev checked exception
 */
public class UnidevException extends Exception {

    private static final long serialVersionUID = 1L;

    public UnidevException() {
    }

    public UnidevException(String message) {
        super(message);
    }

    public UnidevException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnidevException(Throwable cause) {
        super(cause);
    }
}
