/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.validation;

/**
 * An interface to impose restrictions and requirements on validations (e.g.
 * renew=true).
 *
 * @author William G. Thompson, Jr.
 * @version $Revision$ $Date$
 * @since 3.0
 * <p>
 * This is a published and supported CAS Server 3 API.
 * </p>
 */
public interface ValidationSpecification {

    /**
     * @param assertion The assertion we want to confirm is satisfied by this
     *                  spec.
     * @return true if it is, false otherwise.
     */
    boolean isSatisfiedBy(Assertion assertion);
}
