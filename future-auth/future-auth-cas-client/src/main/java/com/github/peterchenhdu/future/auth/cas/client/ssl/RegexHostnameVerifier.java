/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.client.ssl;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.util.regex.Pattern;

/**
 * Validates an SSL peer's hostname using a regular expression that a candidate
 * host must match in order to be verified.
 *
 * @author Marvin Addison
 * @version $Revision: 22945 $ $Date: 2011-02-12 13:30:25 -0500 (Sat, 12 Feb 2011) $
 * @since 3.1.10
 *
 */
public final class RegexHostnameVerifier implements HostnameVerifier {

    /** Allowed hostname pattern */
    private Pattern pattern;
    
    
    /**
     * Creates a new instance using the given regular expression.
     * 
     * @param regex Regular expression describing allowed hosts.
     */
    public RegexHostnameVerifier(final String regex) {
        this.pattern = Pattern.compile(regex);
    }


    /** {@inheritDoc} */
    public boolean verify(final String hostname, final SSLSession session) {
        return pattern.matcher(hostname).matches();
    }
}
