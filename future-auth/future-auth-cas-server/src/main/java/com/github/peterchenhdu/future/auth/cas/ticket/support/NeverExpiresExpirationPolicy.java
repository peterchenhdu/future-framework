/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.ticket.support;

import com.github.peterchenhdu.future.auth.cas.ticket.ExpirationPolicy;
import com.github.peterchenhdu.future.auth.cas.ticket.TicketState;

/**
 * NeverExpiresExpirationPolicy always answers false when asked if a Ticket is
 * expired. Use this policy when you want a Ticket to live forever, or at least
 * as long as the particular CAS Universe exists.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0
 */
public final class NeverExpiresExpirationPolicy implements ExpirationPolicy {

    /**
     * Serializable Unique ID.
     */
    private static final long serialVersionUID = 3833747698242303540L;

    public boolean isExpired(final TicketState ticketState) {
        return false;
    }
}
