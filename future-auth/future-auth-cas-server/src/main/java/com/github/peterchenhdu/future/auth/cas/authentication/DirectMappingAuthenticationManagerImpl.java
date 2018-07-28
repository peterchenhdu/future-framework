/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.authentication;

import com.github.peterchenhdu.future.auth.cas.authentication.handler.AuthenticationException;
import com.github.peterchenhdu.future.auth.cas.authentication.handler.AuthenticationHandler;
import com.github.peterchenhdu.future.auth.cas.authentication.handler.BadCredentialsAuthenticationException;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.Credentials;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.CredentialsToPrincipalResolver;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.Principal;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * Authentication Manager that provides a direct mapping between credentials
 * provided and the authentication handler used to authenticate the user.
 * 
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1
 */
public final class DirectMappingAuthenticationManagerImpl extends AbstractAuthenticationManager {

    @NotNull
    @Size(min=1)
    private Map<Class< ? extends Credentials>, DirectAuthenticationHandlerMappingHolder> credentialsMapping;

    /**
     * @throws IllegalArgumentException if a mapping cannot be found.
     * @see com.github.peterchenhdu.future.auth.cas.authentication.AuthenticationManager#authenticate(com.github.peterchenhdu.future.auth.cas.authentication.principal.Credentials)
     */
    @Override
    protected Pair<AuthenticationHandler, Principal> authenticateAndObtainPrincipal(final Credentials credentials) throws AuthenticationException {
        final Class< ? extends Credentials> credentialsClass = credentials.getClass();
        final DirectAuthenticationHandlerMappingHolder d = this.credentialsMapping.get(credentialsClass);

        Assert.notNull(d, "no mapping found for: " + credentialsClass.getName());

        final String handlerName = d.getAuthenticationHandler().getClass().getSimpleName();
        boolean authenticated = false;

        try {
            authenticated = d.getAuthenticationHandler().authenticate(credentials);
        } catch (final Exception e) {
            handleError(handlerName, credentials, e);
        }

        if (!authenticated) {
            log.info("{} failed to authenticate {}", handlerName, credentials);
            throw BadCredentialsAuthenticationException.ERROR;
        }
        log.info("{} successfully authenticated {}", handlerName, credentials);

        final Principal p = d.getCredentialsToPrincipalResolver().resolvePrincipal(credentials);

        return new Pair<AuthenticationHandler,Principal>(d.getAuthenticationHandler(), p);
    }

    public final void setCredentialsMapping(
        final Map<Class< ? extends Credentials>, DirectAuthenticationHandlerMappingHolder> credentialsMapping) {
        this.credentialsMapping = credentialsMapping;
    }
    
    public static final class DirectAuthenticationHandlerMappingHolder {

        private AuthenticationHandler authenticationHandler;

        private CredentialsToPrincipalResolver credentialsToPrincipalResolver;

        public DirectAuthenticationHandlerMappingHolder() {
            // nothing to do
        }

        public final AuthenticationHandler getAuthenticationHandler() {
            return this.authenticationHandler;
        }

        public void setAuthenticationHandler(
            final AuthenticationHandler authenticationHandler) {
            this.authenticationHandler = authenticationHandler;
        }

        public CredentialsToPrincipalResolver getCredentialsToPrincipalResolver() {
            return this.credentialsToPrincipalResolver;
        }

        public void setCredentialsToPrincipalResolver(
            final CredentialsToPrincipalResolver credentialsToPrincipalResolver) {
            this.credentialsToPrincipalResolver = credentialsToPrincipalResolver;
        }
    }

}
