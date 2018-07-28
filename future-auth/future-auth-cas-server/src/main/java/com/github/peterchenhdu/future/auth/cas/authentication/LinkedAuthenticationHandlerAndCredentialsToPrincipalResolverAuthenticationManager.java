/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.authentication;

import com.github.peterchenhdu.future.auth.cas.authentication.handler.AuthenticationException;
import com.github.peterchenhdu.future.auth.cas.authentication.handler.AuthenticationHandler;
import com.github.peterchenhdu.future.auth.cas.authentication.handler.BadCredentialsAuthenticationException;
import com.github.peterchenhdu.future.auth.cas.authentication.handler.UnsupportedCredentialsException;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.Credentials;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.CredentialsToPrincipalResolver;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.Principal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * Ensures that all authentication handlers are tried, but if one is tried, the associated CredentialsToPrincipalResolver is used.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.3.5
 */
public class LinkedAuthenticationHandlerAndCredentialsToPrincipalResolverAuthenticationManager extends AbstractAuthenticationManager {

    @NotNull
    @Size(min = 1)
    private final Map<AuthenticationHandler, CredentialsToPrincipalResolver> linkedHandlers;

    public LinkedAuthenticationHandlerAndCredentialsToPrincipalResolverAuthenticationManager(final Map<AuthenticationHandler,CredentialsToPrincipalResolver> linkedHandlers) {
        this.linkedHandlers = linkedHandlers; 
    }

    @Override
    protected Pair<AuthenticationHandler, Principal> authenticateAndObtainPrincipal(final Credentials credentials) throws AuthenticationException {
        boolean foundOneThatWorks = false;
        String handlerName;

        for (final AuthenticationHandler authenticationHandler : this.linkedHandlers.keySet()) {
            if (!authenticationHandler.supports(credentials)) {
                continue;
            }

            foundOneThatWorks = true;
            boolean authenticated = false;
            handlerName = authenticationHandler.getClass().getName();
                        
            try {
                authenticated = authenticationHandler.authenticate(credentials);
            } catch (final Exception e) {
                handleError(handlerName, credentials, e);
            }

            if (authenticated) {
                log.info("{} successfully authenticated {}", handlerName, credentials);
                final Principal p = this.linkedHandlers.get(authenticationHandler).resolvePrincipal(credentials);
                return new Pair<AuthenticationHandler,Principal>(authenticationHandler, p);
            }
            log.info("{} failed to authenticate {}", handlerName, credentials);
        }

        if (foundOneThatWorks) {
            throw BadCredentialsAuthenticationException.ERROR;
        }

        throw UnsupportedCredentialsException.ERROR;
    }
}
