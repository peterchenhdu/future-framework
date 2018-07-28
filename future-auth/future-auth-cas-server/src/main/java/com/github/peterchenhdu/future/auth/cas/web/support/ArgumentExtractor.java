/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.web.support;

import com.github.peterchenhdu.future.auth.cas.authentication.principal.WebApplicationService;

import javax.servlet.http.HttpServletRequest;

/**
 * Strategy interface for retrieving services and tickets from the request.
 * <p>
 * These are the two things the CAS protocol and the SAML protocol have in
 * common.
 * 
 * @author Scott Battatglia
 * @version $Revision$ $Date$
 * @since 3.1
 */
public interface ArgumentExtractor {
    /**
     * Retrieve the service from the request.
     * 
     * @param request the request context.
     * @return the fully formed Service or null if it could not be found.
     */
    WebApplicationService extractService(HttpServletRequest request);
}
