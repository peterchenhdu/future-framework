/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.authentication.principal;

/**
 * Implementation of CredentialsToPrincipalResolver for Credentials based on
 * UsernamePasswordCredentials when a SimplePrincipal (username only) is
 * sufficient.
 * <p>
 * Implementation extracts the username from the Credentials provided and
 * constructs a new SimplePrincipal with the unique id set to the username.
 * </p>
 *
 * @author Scott Battaglia
 * @version $Revision: 1.2 $ $Date: 2007/01/22 20:35:26 $
 * @see com.github.peterchenhdu.future.auth.cas.authentication.principal.SimplePrincipal
 * @since 3.0
 */
public final class UsernamePasswordCredentialsToPrincipalResolver extends
        AbstractPersonDirectoryCredentialsToPrincipalResolver {

    protected String extractPrincipalId(final Credentials credentials) {
        final UsernamePasswordCredentials usernamePasswordCredentials = (UsernamePasswordCredentials) credentials;
        return usernamePasswordCredentials.getUsername();
    }

    /**
     * Return true if Credentials are UsernamePasswordCredentials, false
     * otherwise.
     */
    public boolean supports(final Credentials credentials) {
        return credentials != null
                && UsernamePasswordCredentials.class.isAssignableFrom(credentials
                .getClass());
    }
}
