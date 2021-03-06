/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas.client.proxy;

import java.util.TimerTask;

/**
 * A {@link TimerTask} implementation which performs the
 * actual 'cleaning' by calling {@link ProxyGrantingTicketStorage#cleanUp()}.
 * <p>
 * By default, the {@link com.github.peterchenhdu.future.auth.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter} configures
 * a task that cleans up the {@link com.github.peterchenhdu.future.auth.cas.client.proxy.ProxyGrantingTicketStorage} associated with it.
 *
 * @author Brad Cupit (brad [at] lsu {dot} edu)
 * @version $Revision$ $Date$
 * @since 3.1.6
 */
public final class CleanUpTimerTask extends TimerTask {

    private final ProxyGrantingTicketStorage proxyGrantingTicketStorage;

    public CleanUpTimerTask(final ProxyGrantingTicketStorage proxyGrantingTicketStorage) {
        this.proxyGrantingTicketStorage = proxyGrantingTicketStorage;
    }

    public void run() {
        this.proxyGrantingTicketStorage.cleanUp();
    }
}
