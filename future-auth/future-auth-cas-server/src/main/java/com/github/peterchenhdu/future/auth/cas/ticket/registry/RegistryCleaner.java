/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas.ticket.registry;

/**
 * Strategy interface to denote the start of cleaning the registry.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0
 * <p>
 * This is a published and supported CAS Server 3 API.
 * </p>
 */
public interface RegistryCleaner {

    /**
     * Method to kick-off the cleaning of a registry.
     */
    void clean();
}
