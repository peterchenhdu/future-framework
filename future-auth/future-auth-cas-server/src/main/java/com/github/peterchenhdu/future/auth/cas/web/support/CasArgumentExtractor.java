/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.web.support;

import com.github.peterchenhdu.future.auth.cas.authentication.principal.SimpleWebApplicationServiceImpl;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.WebApplicationService;

import javax.servlet.http.HttpServletRequest;

/**
 * Implements the traditional CAS2 protocol.  Accepts an HttpClient reference.  A default
 * one is configured that you can override.
 * 
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1
 */
public final class CasArgumentExtractor extends AbstractSingleSignOutEnabledArgumentExtractor {

    public final WebApplicationService extractServiceInternal(final HttpServletRequest request) {
        return SimpleWebApplicationServiceImpl.createServiceFrom(request, getHttpClientIfSingleSignOutEnabled());
    }
}
