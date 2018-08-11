/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.authentication;

import com.github.inspektr.audit.annotation.Audit;
import com.github.peterchenhdu.future.auth.cas.authentication.handler.AuthenticationException;
import com.github.peterchenhdu.future.auth.cas.authentication.handler.AuthenticationHandler;
import com.github.peterchenhdu.future.auth.cas.authentication.handler.NamedAuthenticationHandler;
import com.github.peterchenhdu.future.auth.cas.authentication.handler.UncategorizedAuthenticationException;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.Credentials;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.Principal;
import org.perf4j.aop.Profiled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.3.5
 */
public abstract class AbstractAuthenticationManager implements AuthenticationManager {

    /**
     * Log instance for logging events, errors, warnings, etc.
     */
    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * An array of AuthenticationAttributesPopulators.
     */
    @NotNull
    private List<AuthenticationMetaDataPopulator> authenticationMetaDataPopulators = new ArrayList<AuthenticationMetaDataPopulator>();

    @Audit(
            action = "AUTHENTICATION",
            actionResolverName = "AUTHENTICATION_RESOLVER",
            resourceResolverName = "AUTHENTICATION_RESOURCE_RESOLVER")
    @Profiled(tag = "AUTHENTICATE", logFailuresSeparately = false)
    public final Authentication authenticate(final Credentials credentials) throws AuthenticationException {

        final Pair<AuthenticationHandler, Principal> pair = authenticateAndObtainPrincipal(credentials);

        // we can only get here if the above method doesn't throw an exception. And if it doesn't, then the pair must not be null.
        final Principal p = pair.getSecond();
// TODO       log.info("{} authenticated {} with credential {}.", pair.getFirst(), p, credentials);
// TODO       log.debug("Attribute map for {}: {}", p.getId(), p.getAttributes());

        Authentication authentication = new MutableAuthentication(p);

        if (pair.getFirst() instanceof NamedAuthenticationHandler) {
            final NamedAuthenticationHandler a = (NamedAuthenticationHandler) pair.getFirst();
            authentication.getAttributes().put(AuthenticationManager.AUTHENTICATION_METHOD_ATTRIBUTE, a.getName());
        }

        if (this.authenticationMetaDataPopulators != null) {

            for (final AuthenticationMetaDataPopulator authenticationMetaDataPopulator : this.authenticationMetaDataPopulators) {
                authentication = authenticationMetaDataPopulator
                        .populateAttributes(authentication, credentials);
            }
        }


        return new ImmutableAuthentication(authentication.getPrincipal(),
                authentication.getAttributes());
    }

    /**
     * @param authenticationMetaDataPopulators the authenticationMetaDataPopulators to set.
     */
    public final void setAuthenticationMetaDataPopulators(final List<AuthenticationMetaDataPopulator> authenticationMetaDataPopulators) {
        this.authenticationMetaDataPopulators = authenticationMetaDataPopulators;
    }

    /**
     * Follows the same rules as the "authenticate" method (i.e. should only return a fully populated object, or throw an exception)
     *
     * @param credentials the credentials to check
     * @return the pair of authentication handler and principal.  CANNOT be NULL.
     * @throws AuthenticationException if there is an error authenticating.
     */
    protected abstract Pair<AuthenticationHandler, Principal> authenticateAndObtainPrincipal(Credentials credentials) throws AuthenticationException;


    /**
     * Handles an authentication error raised by an {@link AuthenticationHandler}.
     *
     * @param handlerName The class name of the authentication handler.
     * @param credentials Client credentials subject to authentication.
     * @param e           The exception that has occurred during authentication attempt.
     */
    protected void handleError(final String handlerName, final Credentials credentials, final Exception e)
            throws AuthenticationException {
        if (e instanceof AuthenticationException) {
            // CAS-1181 Log common authentication failures at INFO without stack trace
            log.info("{} failed authenticating {}", handlerName, credentials);
            throw (AuthenticationException) e;
        }
        log.error("{} threw error authenticating {}", handlerName, credentials, e);
        throw new UncategorizedAuthenticationException(e.getClass().getSimpleName(), e.getMessage());
    }


    protected static class Pair<A, B> {

        private final A first;

        private final B second;

        public Pair(final A first, final B second) {
            this.first = first;
            this.second = second;
        }

        public A getFirst() {
            return this.first;
        }


        public B getSecond() {
            return this.second;
        }
    }

}
