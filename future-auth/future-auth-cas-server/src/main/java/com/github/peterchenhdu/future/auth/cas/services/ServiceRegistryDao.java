/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.services;

import java.util.List;

/**
 * Registry of all RegisteredServices.
 * 
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1
 */
public interface ServiceRegistryDao {

    /**
     * Persist the service in the data store.
     * 
     * @param registeredService the service to persist.
     * @return the updated RegisteredService.
     */
    RegisteredService save(RegisteredService registeredService);

    /**
     * Remove the service from the data store.
     * 
     * @param registeredService the service to remove.
     * @return true if it was removed, false otherwise.
     */
    boolean delete(RegisteredService registeredService);

    /**
     * Retrieve the services from the data store.
     * 
     * @return the collection of services.
     */
    List<RegisteredService> load();
    
    RegisteredService findServiceById(long id);
}
