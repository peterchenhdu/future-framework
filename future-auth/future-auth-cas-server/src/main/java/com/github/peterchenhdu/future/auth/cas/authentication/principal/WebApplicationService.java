/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.authentication.principal;

/**
 * Represents a service using CAS that comes from the web.
 * 
 * @author Scott Battaglia
 * @version $Revision: 1.3 $ $Date: 2007/02/27 19:31:58 $
 * @since 3.1
 */
public interface WebApplicationService extends Service {

    /**
     * Constructs the url to redirect the service back to.
     * 
     * @param ticketId the service ticket to provide to the service.
     * @return the redirect url.
     */
    Response getResponse(String ticketId);

    /**
     * Retrieves the artifact supplied with the service. May be null.
     * 
     * @return the artifact if it exists, null otherwise.
     */
    String getArtifactId();
}
