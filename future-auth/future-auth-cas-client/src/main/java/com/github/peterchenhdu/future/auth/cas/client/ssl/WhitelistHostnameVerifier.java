/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas.client.ssl;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Verifies a SSL peer host name based on an explicit whitelist of allowed hosts.
 *
 * @author Marvin Addison
 * @version $Revision: 22945 $ $Date: 2011-02-12 13:30:25 -0500 (Sat, 12 Feb 2011) $
 * @since 3.1.10
 *
 */
public final class WhitelistHostnameVerifier implements HostnameVerifier {

    /** Allowed hosts */
    private String[] allowedHosts;


    /**
     * Creates a new instance using the given array of allowed hosts.
     * 
     * @param allowed Array of allowed hosts.
     */
    public WhitelistHostnameVerifier(final String[] allowed) {
        this.allowedHosts = allowed;
    }


    /**
     * Creates a new instance using the given list of allowed hosts.
     * 
     * @param allowedList Comma-separated list of allowed hosts.
     */
    public WhitelistHostnameVerifier(final String allowedList) {
        this.allowedHosts = allowedList.split(",\\s*");
    }

    /** {@inheritDoc} */
    public boolean verify(final String hostname, final SSLSession session) {

        for (final String allowedHost : this.allowedHosts) {
            if (hostname.equalsIgnoreCase(allowedHost)) {
                return true;
            }
        }
        return false;
    }

}
