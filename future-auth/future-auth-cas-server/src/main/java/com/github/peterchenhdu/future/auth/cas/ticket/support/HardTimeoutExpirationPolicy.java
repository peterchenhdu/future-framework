/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.ticket.support;

import com.github.peterchenhdu.future.auth.cas.ticket.ExpirationPolicy;
import com.github.peterchenhdu.future.auth.cas.ticket.TicketState;

/**
 * Ticket expiration policy based on a hard timeout from ticket creation time rather than the
 * "idle" timeout provided by {@link com.github.peterchenhdu.future.auth.cas.ticket.support.TimeoutExpirationPolicy}.
 *
 * @author Andrew Feller
 * @version $Revision$ $Date$
 * @since 3.1.2
 */
public final class HardTimeoutExpirationPolicy implements ExpirationPolicy {

    /**
     * Unique Id for serialization.
     */
    private static final long serialVersionUID = -1465997330804816888L;

    /**
     * The time to kill in milliseconds.
     */
    private final long timeToKillInMilliSeconds;

    public HardTimeoutExpirationPolicy(final long timeToKillInMilliSeconds) {
        this.timeToKillInMilliSeconds = timeToKillInMilliSeconds;
    }

    public boolean isExpired(final TicketState ticketState) {
        return (ticketState == null)
                || (System.currentTimeMillis() - ticketState.getCreationTime() >= this.timeToKillInMilliSeconds);
    }
}
