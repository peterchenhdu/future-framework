/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas.client.jaas;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 * Callback handler that provides the CAS service and ticket to a
 * {@link NameCallback} and {@link PasswordCallback} respectively,
 * which meets the requirements of the {@link CasLoginModule} JAAS module.
 *
 * @author Marvin S. Addison
 * @version $Revision: 22086 $
 * @since 3.1.11
 */
public class ServiceAndTicketCallbackHandler implements CallbackHandler {

    /**
     * CAS service URL
     */
    private final String service;

    /**
     * CAS service ticket
     */
    private final String ticket;

    /**
     * Creates a new instance with the given service and ticket.
     *
     * @param service CAS service URL.
     * @param ticket  CAS service ticket.
     */
    public ServiceAndTicketCallbackHandler(final String service, final String ticket) {
        this.service = service;
        this.ticket = ticket;
    }

    public void handle(final Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (final Callback callback : callbacks) {
            if (callback instanceof NameCallback) {
                ((NameCallback) callback).setName(this.service);
            } else if (callback instanceof PasswordCallback) {
                ((PasswordCallback) callback).setPassword(this.ticket.toCharArray());
            } else {
                throw new UnsupportedCallbackException(callback, "Callback not supported.");
            }
        }
    }

}
