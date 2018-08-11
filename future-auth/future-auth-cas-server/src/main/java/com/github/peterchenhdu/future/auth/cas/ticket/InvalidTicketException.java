/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.ticket;

/**
 * TicketException to alert that a Ticket was not found or that it is expired.
 *
 * @author Scott Battaglia
 * @version $Revison$ $Date$
 * @since 3.0
 */
public class InvalidTicketException extends TicketException {

    /**
     * The Unique Serializable ID.
     */
    private static final long serialVersionUID = 3256723974594508849L;

    /**
     * The code description.
     */
    private static final String CODE = "INVALID_TICKET";

    /**
     * Constructs a InvalidTicketException with the default exception code.
     */
    public InvalidTicketException() {
        super(CODE);
    }

    /**
     * Constructs a InvalidTicketException with the default exception code and
     * the original exception that was thrown.
     *
     * @param throwable the chained exception
     */
    public InvalidTicketException(final Throwable throwable) {
        super(CODE, throwable);
    }
}
