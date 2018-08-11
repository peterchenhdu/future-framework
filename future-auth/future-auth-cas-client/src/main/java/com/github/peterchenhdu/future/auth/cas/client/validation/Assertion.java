/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas.client.validation;

import com.github.peterchenhdu.future.auth.cas.client.authentication.AttributePrincipal;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Represents a response to a validation request.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1
 */
public interface Assertion extends Serializable {

    /**
     * The date from which the assertion is valid from.
     *
     * @return the valid from date.
     */
    Date getValidFromDate();

    /**
     * The date which the assertion is valid until.
     *
     * @return the valid until date.
     */
    Date getValidUntilDate();

    /**
     * The key/value pairs associated with this assertion.
     *
     * @return the map of attributes.
     */
    Map<String, Object> getAttributes();

    /**
     * The principal for which this assertion is valid.
     *
     * @return the principal.
     */
    AttributePrincipal getPrincipal();
}
