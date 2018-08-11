/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.authentication.principal;

/**
 * Credentials that wish to handle remember me scenarios need
 * to implement this class.
 *
 * @author Scott Battaglia
 * @version $Revision: 1.1 $ $Date: 2005/08/19 18:27:17 $
 * @since 3.2.1
 */
public interface RememberMeCredentials extends Credentials {

    String AUTHENTICATION_ATTRIBUTE_REMEMBER_ME = "com.github.peterchenhdu.future.auth.cas.authentication.principal.REMEMBER_ME";

    String REQUEST_PARAMETER_REMEMBER_ME = "rememberMe";

    boolean isRememberMe();

    void setRememberMe(boolean rememberMe);
}
