/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.ticket.support;

import com.github.peterchenhdu.future.auth.cas.authentication.principal.RememberMeCredentials;
import com.github.peterchenhdu.future.auth.cas.ticket.ExpirationPolicy;
import com.github.peterchenhdu.future.auth.cas.ticket.TicketState;

import javax.validation.constraints.NotNull;

/**
 * Delegates to different expiration policies depending on whether remember me
 * is true or not.
 * 
 * @author Scott Battaglia
 * @version $Revision: 1.1 $ $Date: 2005/08/19 18:27:17 $
 * @since 3.2.1
 *
 */
public final class RememberMeDelegatingExpirationPolicy implements ExpirationPolicy {
    
    /** Unique Id for Serialization */
    private static final long serialVersionUID = -575145836880428365L;

    @NotNull
    private ExpirationPolicy rememberMeExpirationPolicy;
    
    @NotNull
    private ExpirationPolicy sessionExpirationPolicy;

    public boolean isExpired(TicketState ticketState) {
        final Boolean b = (Boolean) ticketState.getAuthentication().getAttributes().get(RememberMeCredentials.AUTHENTICATION_ATTRIBUTE_REMEMBER_ME);
        
        if (b == null || b.equals(Boolean.FALSE)) {
            return this.sessionExpirationPolicy.isExpired(ticketState);
        }
        
        return this.rememberMeExpirationPolicy.isExpired(ticketState);
    }
    
    public void setRememberMeExpirationPolicy(
        final ExpirationPolicy rememberMeExpirationPolicy) {
        this.rememberMeExpirationPolicy = rememberMeExpirationPolicy;
    }

    public void setSessionExpirationPolicy(final ExpirationPolicy sessionExpirationPolicy) {
        this.sessionExpirationPolicy = sessionExpirationPolicy;
    }
}
