/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.ticket.registry.support;


/**
 * No-Op locking strategy that allows the use of {@link DefaultTicketRegistryCleaner}
 * in environments where exclusive access to the registry for cleaning is either
 * unnecessary or not possible.
 *
 * @author Marvin Addison
 * @version $Revision$
 * @since 3.3.6
 *
 */
public class NoOpLockingStrategy implements LockingStrategy {

    /**
     * @see com.github.peterchenhdu.future.auth.cas.ticket.registry.support.LockingStrategy#acquire()
     */
    public boolean acquire() {
        return true;
    }

    /**
     * @see com.github.peterchenhdu.future.auth.cas.ticket.registry.support.LockingStrategy#release()
     */
    public void release() {
        // Nothing to release
    }

}
