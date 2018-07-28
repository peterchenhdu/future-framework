/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.services;

/**
 * Exception that is thrown when an Unauthorized Service attempts to use CAS.
 * 
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0
 */
public class UnauthorizedServiceException extends RuntimeException {

    /** The Unique ID for serialization. */
    private static final long serialVersionUID = 3905807495715960369L;
    
    /** The code description. */
    private static final String CODE = "service.not.authorized";

    public UnauthorizedServiceException() {
        this(CODE);
    }
    
    
    /**
     * Constructs an UnauthorizedServiceException with a custom message and the
     * root cause of this exception.
     * 
     * @param message an explanatory message.
     * @param cause the root cause of the exception.
     */
    public UnauthorizedServiceException(final String message,
        final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an exception with a custom message.
     * 
     * @param message an explanatory message.
     */
    public UnauthorizedServiceException(final String message) {
        super(message);
    }
}
