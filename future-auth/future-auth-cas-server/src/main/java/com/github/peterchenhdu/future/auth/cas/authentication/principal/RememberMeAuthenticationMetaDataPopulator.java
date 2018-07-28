/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.authentication.principal;

import com.github.peterchenhdu.future.auth.cas.authentication.Authentication;
import com.github.peterchenhdu.future.auth.cas.authentication.AuthenticationMetaDataPopulator;

/**
 * Determines if the credentials provided are for Remember Me Services and then sets the appropriate
 * Authentication attribute if remember me services have been requested.
 * 
 * @author Scott Battaglia
 * @version $Revision: 1.1 $ $Date: 2005/08/19 18:27:17 $
 * @since 3.2.1
 *
 */
public final class RememberMeAuthenticationMetaDataPopulator implements
    AuthenticationMetaDataPopulator {

    public Authentication populateAttributes(final Authentication authentication,
        final Credentials credentials) {
        if (credentials instanceof RememberMeCredentials) {
            final RememberMeCredentials r = (RememberMeCredentials) credentials;
            if (r.isRememberMe()) {
                authentication.getAttributes().put(RememberMeCredentials.AUTHENTICATION_ATTRIBUTE_REMEMBER_ME, Boolean.TRUE);
            }
        }
        
        return authentication;
    }
}
