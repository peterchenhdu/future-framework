/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.web.support;

import com.github.peterchenhdu.future.auth.cas.authentication.principal.SamlService;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.WebApplicationService;

import javax.servlet.http.HttpServletRequest;

/**
 * Retrieve the ticket and artifact based on the SAML 1.1 profile.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1
 */
public final class SamlArgumentExtractor extends AbstractSingleSignOutEnabledArgumentExtractor {

    public WebApplicationService extractServiceInternal(final HttpServletRequest request) {
        return SamlService.createServiceFrom(request, getHttpClientIfSingleSignOutEnabled());
    }
}
