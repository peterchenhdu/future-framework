/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.ticket.support;

import com.github.peterchenhdu.future.auth.cas.ticket.ExpirationPolicy;
import com.github.peterchenhdu.future.auth.cas.ticket.TicketState;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

/**
 * ExpirationPolicy that is based on certain number of uses of a ticket or a
 * certain time period for a ticket to exist.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0
 */
public final class MultiTimeUseOrTimeoutExpirationPolicy implements
        ExpirationPolicy {

    /**
     * Serializable Unique ID.
     */
    private static final long serialVersionUID = 3257844372614558261L;

    /**
     * The time to kill in millseconds.
     */
    private final long timeToKillInMilliSeconds;

    /**
     * The maximum number of uses before expiration.
     */
    private final int numberOfUses;

    public MultiTimeUseOrTimeoutExpirationPolicy(final int numberOfUses,
                                                 final long timeToKillInMilliSeconds) {
        this.timeToKillInMilliSeconds = timeToKillInMilliSeconds;
        this.numberOfUses = numberOfUses;
        Assert.isTrue(this.numberOfUses > 0, "numberOfUsers must be greater than 0.");
        Assert.isTrue(this.timeToKillInMilliSeconds > 0, "timeToKillInMilliseconds must be greater than 0.");

    }

    public MultiTimeUseOrTimeoutExpirationPolicy(int numberOfUses, long timeToKill, TimeUnit timeUnit) {
        this(numberOfUses, timeUnit.toMillis(timeToKill));
    }

    public boolean isExpired(final TicketState ticketState) {
        return (ticketState == null)
                || (ticketState.getCountOfUses() >= this.numberOfUses)
                || (System.currentTimeMillis() - ticketState.getLastTimeUsed() >= this.timeToKillInMilliSeconds);
    }
}
