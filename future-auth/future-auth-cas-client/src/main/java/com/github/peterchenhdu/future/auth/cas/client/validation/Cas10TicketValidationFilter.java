/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas.client.validation;

import javax.servlet.FilterConfig;

/**
 * Implementation of AbstractTicketValidatorFilter that instanciates a Cas10TicketValidator.
 * <p>Deployers can provide the "casServerPrefix" and the "renew" attributes via the standard context or filter init
 * parameters.
 * 
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1
 */
public class Cas10TicketValidationFilter extends AbstractTicketValidationFilter {

    protected final TicketValidator getTicketValidator(final FilterConfig filterConfig) {
        final String casServerUrlPrefix = getPropertyFromInitParams(filterConfig, "casServerUrlPrefix", null);
        final Cas10TicketValidator validator = new Cas10TicketValidator(casServerUrlPrefix);
        validator.setRenew(parseBoolean(getPropertyFromInitParams(filterConfig, "renew", "false")));
        validator.setHostnameVerifier(getHostnameVerifier(filterConfig));
        validator.setEncoding(getPropertyFromInitParams(filterConfig, "encoding", null));

        return validator;
    }
}
