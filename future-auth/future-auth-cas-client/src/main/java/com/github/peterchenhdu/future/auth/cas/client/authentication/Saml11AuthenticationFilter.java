/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas.client.authentication;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

/**
 * Extension to the default Authentication filter that sets the required SAML1.1 artifact parameter name and service parameter name.
 * <p>
 * Note, the "final" on this class helps ensure the compliance required in the initInternal method.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1.12
 */
public final class Saml11AuthenticationFilter extends AuthenticationFilter {

    protected void initInternal(final FilterConfig filterConfig) throws ServletException {
        super.initInternal(filterConfig);

        log.warn("SAML1.1 compliance requires the [artifactParameterName] and [serviceParameterName] to be set to specified values.");
        log.warn("This filter will overwrite any user-provided values (if any are provided)");

        setArtifactParameterName("SAMLart");
        setServiceParameterName("TARGET");
    }
}
