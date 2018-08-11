/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.authentication;

import com.github.peterchenhdu.future.auth.cas.authentication.principal.Principal;

import java.util.Date;
import java.util.HashMap;

/**
 * Mutable implementation of Authentication interface.
 * <p>
 * Instanciators of the MutableAuthentication class must take care that the map
 * they provide is serializable (i.e. HashMap).
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0.3
 */
public final class MutableAuthentication extends AbstractAuthentication {

    /**
     * Unique Id for serialization.
     */
    private static final long serialVersionUID = -4415875344376642246L;

    /**
     * The date/time this authentication object became valid.
     */
    private final Date authenticatedDate;

    public MutableAuthentication(final Principal principal) {
        this(principal, new Date());
    }

    public MutableAuthentication(final Principal principal, final Date date) {
        super(principal, new HashMap<String, Object>());
        this.authenticatedDate = date;
    }

    public Date getAuthenticatedDate() {
        return this.authenticatedDate;
    }
}
