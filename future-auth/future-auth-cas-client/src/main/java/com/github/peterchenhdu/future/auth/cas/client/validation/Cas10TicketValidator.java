/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas.client.validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * Implementation of a Ticket Validator that can validate tickets conforming to the CAS 1.0 specification.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1
 */
public final class Cas10TicketValidator extends AbstractCasProtocolUrlBasedTicketValidator {

    public Cas10TicketValidator(final String casServerUrlPrefix) {
        super(casServerUrlPrefix);
    }

    protected String getUrlSuffix() {
        return "validate";
    }

    protected Assertion parseResponseFromServer(final String response) throws TicketValidationException {
        if (!response.startsWith("yes")) {
            throw new TicketValidationException("CAS Server could not validate ticket.");
        }

        try {
            final BufferedReader reader = new BufferedReader(new StringReader(response));
            reader.readLine();
            final String name = reader.readLine();

            return new AssertionImpl(name);
        } catch (final IOException e) {
            throw new TicketValidationException("Unable to parse response.", e);
        }
    }
}
