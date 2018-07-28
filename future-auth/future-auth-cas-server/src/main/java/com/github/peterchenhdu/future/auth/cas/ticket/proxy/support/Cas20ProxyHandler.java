/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.ticket.proxy.support;

import com.github.peterchenhdu.future.auth.cas.authentication.principal.Credentials;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.HttpBasedServiceCredentials;
import com.github.peterchenhdu.future.auth.cas.ticket.proxy.ProxyHandler;
import com.github.peterchenhdu.future.auth.cas.util.DefaultUniqueTicketIdGenerator;
import com.github.peterchenhdu.future.auth.cas.util.HttpClient;
import com.github.peterchenhdu.future.auth.cas.util.UniqueTicketIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;

/**
 * Proxy Handler to handle the default callback functionality of CAS 2.0.
 * <p>
 * The default behavior as defined in the CAS 2 Specification is to callback the
 * URL provided and give it a pgtIou and a pgtId.
 * </p>
 * 
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0
 */
public final class Cas20ProxyHandler implements ProxyHandler {

    /** The Commons Logging instance. */
    private final Logger log = LoggerFactory.getLogger(getClass());

    /** The PGTIOU ticket prefix. */
    private static final String PGTIOU_PREFIX = "PGTIOU";

    /** Generate unique ids. */
    @NotNull
    private UniqueTicketIdGenerator uniqueTicketIdGenerator = new DefaultUniqueTicketIdGenerator();

    /** Instance of Apache Commons HttpClient */
    @NotNull
    private HttpClient httpClient;

    public String handle(final Credentials credentials,
        final String proxyGrantingTicketId) {
        final HttpBasedServiceCredentials serviceCredentials = (HttpBasedServiceCredentials) credentials;
        final String proxyIou = this.uniqueTicketIdGenerator
            .getNewTicketId(PGTIOU_PREFIX);
        final String serviceCredentialsAsString = serviceCredentials.getCallbackUrl().toExternalForm();
        final StringBuilder stringBuffer = new StringBuilder(
            serviceCredentialsAsString.length() + proxyIou.length()
                + proxyGrantingTicketId.length() + 15);

        stringBuffer.append(serviceCredentialsAsString);

        if (serviceCredentials.getCallbackUrl().getQuery() != null) {
            stringBuffer.append("&");
        } else {
            stringBuffer.append("?");
        }

        stringBuffer.append("pgtIou=");
        stringBuffer.append(proxyIou);
        stringBuffer.append("&pgtId=");
        stringBuffer.append(proxyGrantingTicketId);

        if (this.httpClient.isValidEndPoint(stringBuffer.toString())) {
            if (log.isDebugEnabled()) {
                log.debug("Sent ProxyIou of " + proxyIou + " for service: "
                    + serviceCredentials.toString());
            }
            return proxyIou;
        }

        if (log.isDebugEnabled()) {
            log.debug("Failed to send ProxyIou of " + proxyIou
                + " for service: " + serviceCredentials.toString());
        }
        return null;
    }

    /**
     * @param uniqueTicketIdGenerator The uniqueTicketIdGenerator to set.
     */
    public void setUniqueTicketIdGenerator(
        final UniqueTicketIdGenerator uniqueTicketIdGenerator) {
        this.uniqueTicketIdGenerator = uniqueTicketIdGenerator;
    }

    public void setHttpClient(final HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
