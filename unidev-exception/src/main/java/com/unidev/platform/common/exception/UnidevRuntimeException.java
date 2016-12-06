package com.unidev.platform.common.exception;

/**
 * Generic runtime exception object
 */
public class UnidevRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnidevRuntimeException() {
        super();
    }
    public UnidevRuntimeException(String message) {
        super(message);
    }
    public UnidevRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
    public UnidevRuntimeException(Throwable cause) {
        super(cause);
    }
}
