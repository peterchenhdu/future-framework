/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.ticket.proxy.support;

import com.github.peterchenhdu.future.auth.cas.authentication.principal.Credentials;
import com.github.peterchenhdu.future.auth.cas.ticket.proxy.ProxyHandler;

/**
 * Dummy ProxyHandler that does nothing. Useful for Cas 1.0 compliance as CAS
 * 1.0 has no proxying capabilities.
 * 
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0
 */
public final class Cas10ProxyHandler implements ProxyHandler {

    public String handle(final Credentials credentials,
        final String proxyGrantingTicketId) {
        return null;
    }

}
