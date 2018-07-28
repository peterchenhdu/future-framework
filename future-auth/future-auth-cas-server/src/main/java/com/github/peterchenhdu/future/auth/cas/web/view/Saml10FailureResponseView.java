/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.web.view;

import java.util.Map;

import org.opensaml.saml1.core.Response;
import org.opensaml.saml1.core.StatusCode;

/**
 * Represents a failed attempt at validating a ticket, responding via a SAML SOAP message.
 *
 * @author Scott Battaglia
 * @author Marvin S. Addison
 * @since 3.1
 */
public final class Saml10FailureResponseView extends AbstractSaml10ResponseView {

    @Override
    protected void prepareResponse(final Response response, final Map<String, Object> model) {
        response.setStatus(newStatus(StatusCode.REQUEST_DENIED, (String) model.get("description")));
    }
}
