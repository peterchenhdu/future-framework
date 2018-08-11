/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.ticket;

import com.github.peterchenhdu.future.auth.cas.authentication.principal.Service;

/**
 * Exception to alert that there was an error validating the ticket.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0
 */
public class TicketValidationException extends TicketException {

    /**
     * Unique Serial ID.
     */
    private static final long serialVersionUID = 3257004341537093175L;

    /**
     * The code description.
     */
    private static final String CODE = "INVALID_SERVICE";

    private final Service service;

    /**
     * Constructs a TicketValidationException with the default exception code
     * and the original exception that was thrown.
     *
     * @param throwable the chained exception
     */
    public TicketValidationException(final Service service) {
        super(CODE);
        this.service = service;
    }

    public Service getOriginalService() {
        return this.service;
    }

}
