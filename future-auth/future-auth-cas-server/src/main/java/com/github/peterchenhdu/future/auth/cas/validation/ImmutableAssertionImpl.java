/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.validation;

import com.github.peterchenhdu.future.auth.cas.authentication.Authentication;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

/**
 * Default implementation of the Assertion interface which returns the minimum
 * number of attributes required to conform to the CAS 2 protocol.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0
 */
public final class ImmutableAssertionImpl implements Assertion {

    /**
     * Unique Id for Serialization.
     */
    private static final long serialVersionUID = -1921502350732798866L;

    /**
     * The list of principals.
     */
    private final List<Authentication> principals;

    /**
     * Was this the result of a new login.
     */
    private final boolean fromNewLogin;

    /**
     * The service we are asserting this ticket for.
     */
    private final Service service;

    /**
     * Constructs a new ImmutableAssertion out of the given parameters.
     *
     * @param principals   the chain of principals
     * @param service      The service we are asserting this ticket for.
     * @param fromNewLogin was the service ticket from a new login.
     * @throws IllegalArgumentException if there are no principals.
     */
    public ImmutableAssertionImpl(final List<Authentication> principals, final Service service,
                                  final boolean fromNewLogin) {
        Assert.notNull(principals, "principals cannot be null");
        Assert.notNull(service, "service cannot be null");
        Assert.notEmpty(principals, "principals cannot be empty");

        this.principals = principals;
        this.service = service;
        this.fromNewLogin = fromNewLogin;
    }

    public List<Authentication> getChainedAuthentications() {
        return Collections.unmodifiableList(this.principals);
    }

    public boolean isFromNewLogin() {
        return this.fromNewLogin;
    }

    public Service getService() {
        return this.service;
    }

    public boolean equals(final Object o) {
        if (o == null
                || !this.getClass().isAssignableFrom(o.getClass())) {
            return false;
        }

        final Assertion a = (Assertion) o;

        return this.service.equals(a.getService()) && this.fromNewLogin == a.isFromNewLogin() && this.principals.equals(a.getChainedAuthentications());
    }

    public int hashCode() {
        return 15 * this.service.hashCode() ^ this.principals.hashCode();
    }

    public String toString() {
        return "[principals={" + this.principals.toString() + "} for service=" + this.service.toString() + "]";
    }
}
