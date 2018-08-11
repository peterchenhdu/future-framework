/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.services;

/**
 * Exception thrown when a service attempts to proxy when it is not allowed to.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1
 */
public class UnauthorizedProxyingException extends UnauthorizedServiceException {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7307803750894078575L;

    /**
     * The code description.
     */
    private static final String CODE = "service.not.authorized.proxy";

    public UnauthorizedProxyingException() {
        super(CODE);
    }

    public UnauthorizedProxyingException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedProxyingException(String message) {
        super(message);
    }
}
