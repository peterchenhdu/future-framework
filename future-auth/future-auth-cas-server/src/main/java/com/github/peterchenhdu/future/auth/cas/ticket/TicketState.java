/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.ticket;

import com.github.peterchenhdu.future.auth.cas.authentication.Authentication;

/**
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0.5
 */
public interface TicketState {

    /**
     * Returns the number of times a ticket was used.
     *
     * @return the number of times the ticket was used.
     */
    int getCountOfUses();

    /**
     * Returns the last time the ticket was used.
     *
     * @return the last time the ticket was used.
     */
    long getLastTimeUsed();

    /**
     * Get the second to last time used.
     *
     * @return the previous time used.
     */

    long getPreviousTimeUsed();

    /**
     * Get the time the ticket was created.
     *
     * @return the creation time of the ticket.
     */
    long getCreationTime();

    /**
     * Authentication information from the ticket. This may be null.
     *
     * @return the authentication information.
     */
    Authentication getAuthentication();
}
