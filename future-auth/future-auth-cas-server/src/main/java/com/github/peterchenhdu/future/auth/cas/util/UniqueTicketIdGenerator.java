/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.util;

/**
 * Interface that enables for pluggable unique ticket Ids strategies.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0
 * <p>
 * This is a published and supported CAS Server 3 API.
 * </p>
 */
public interface UniqueTicketIdGenerator {

    /**
     * Return a new unique ticket id beginning with the prefix.
     *
     * @param prefix The prefix we want attached to the ticket.
     * @return the unique ticket id
     */
    String getNewTicketId(String prefix);
}
