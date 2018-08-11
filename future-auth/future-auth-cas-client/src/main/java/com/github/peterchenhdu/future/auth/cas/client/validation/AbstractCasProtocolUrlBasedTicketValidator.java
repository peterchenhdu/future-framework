/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas.client.validation;

import com.github.peterchenhdu.future.auth.cas.client.util.CommonUtils;

import java.net.URL;

/**
 * Abstract class that knows the protocol for validating a CAS ticket.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1
 */
public abstract class AbstractCasProtocolUrlBasedTicketValidator extends AbstractUrlBasedTicketValidator {

    protected AbstractCasProtocolUrlBasedTicketValidator(final String casServerUrlPrefix) {
        super(casServerUrlPrefix);
    }

    protected final void setDisableXmlSchemaValidation(final boolean disable) {
        // nothing to do
    }

    /**
     * Retrieves the response from the server by opening a connection and merely reading the response.
     */
    protected final String retrieveResponseFromServer(final URL validationUrl, final String ticket) {
        if (this.hostnameVerifier != null) {
            return CommonUtils.getResponseFromServer(validationUrl, this.hostnameVerifier, getEncoding());
        } else {
            return CommonUtils.getResponseFromServer(validationUrl, getEncoding());
        }
    }
}
