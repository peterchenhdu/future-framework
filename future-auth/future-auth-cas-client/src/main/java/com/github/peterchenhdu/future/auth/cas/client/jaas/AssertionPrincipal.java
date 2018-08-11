/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas.client.jaas;

import com.github.peterchenhdu.future.auth.cas.client.authentication.SimplePrincipal;
import com.github.peterchenhdu.future.auth.cas.client.validation.Assertion;

import java.io.Serializable;

/**
 * Principal implementation that contains the CAS ticket validation assertion.
 *
 * @author Marvin S. Addison
 * @version $Revision: 22071 $
 * @since 3.1.11
 */
public class AssertionPrincipal extends SimplePrincipal implements Serializable {

    /**
     * AssertionPrincipal.java
     */
    private static final long serialVersionUID = 2288520214366461693L;

    /**
     * CAS assertion describing authenticated state
     */
    private Assertion assertion;

    /**
     * Creates a new principal containing the CAS assertion.
     *
     * @param name      Principal name.
     * @param assertion CAS assertion.
     */
    public AssertionPrincipal(final String name, final Assertion assertion) {
        super(name);
        this.assertion = assertion;
    }

    /**
     * @return CAS ticket validation assertion.
     */
    public Assertion getAssertion() {
        return this.assertion;
    }
}
