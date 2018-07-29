/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas.client.ssl;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Hostname verifier that performs no host name verification for an SSL peer
 * such that all hosts are allowed.
 *
 * @author Marvin Addison
 * @version $Revision: 22945 $ $Date: 2011-02-12 13:30:25 -0500 (Sat, 12 Feb 2011) $
 * @since 3.1.10
 */
public final class AnyHostnameVerifier implements HostnameVerifier {

    /** {@inheritDoc} */
    public boolean verify(final String hostname, final SSLSession session) {
        return true;
    }

}
