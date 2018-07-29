/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas.client.jaas;

import java.security.Principal;

/**
 * Strongly-typed wrapper for a ticket credential.
 *
 * @author Marvin S. Addison
 * @version $Revision$ $Date$
 * @since 3.1.12
 *
 */
public final class TicketCredential implements Principal {

    /** Hash code seed value */
    private static final int HASHCODE_SEED = 17;
    
    /** Ticket ID string */
    private String ticket;

    /**
     * Creates a new instance that wraps the given ticket.
     * @param ticket Ticket identifier string.
     */
    public TicketCredential(final String ticket) {
        this.ticket = ticket;
    }

    public String getName() {
        return this.ticket;
    }

    public String toString() {
        return this.ticket;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final TicketCredential that = (TicketCredential) o;

        if (ticket != null ? !ticket.equals(that.ticket) : that.ticket != null) return false;

        return true;
    }

    public int hashCode() {
        int hash = HASHCODE_SEED;
        hash = hash * 31 + (ticket == null ? 0 : ticket.hashCode());
        return hash;
    }
}
