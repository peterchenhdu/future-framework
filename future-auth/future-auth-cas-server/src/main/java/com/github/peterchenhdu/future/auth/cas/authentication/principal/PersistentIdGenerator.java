/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.authentication.principal;

/**
 * Generates a unique consistant Id based on the principal, a service, and some
 * algorithm.
 * 
 * @author Scott Battaglia
 * @version $Revision: 1.1 $ $Date: 2007/04/20 19:39:31 $
 * @since 3.1
 */
public interface PersistentIdGenerator {

    /**
     * Generates a PersistentId based on some algorithm plus the principal and
     * service.
     * 
     * @param principal the principal to generate the id for.
     * @param service the service to generate the id for.
     * @return the generated persistent id.
     */
    String generate(Principal principal, Service service);
}
