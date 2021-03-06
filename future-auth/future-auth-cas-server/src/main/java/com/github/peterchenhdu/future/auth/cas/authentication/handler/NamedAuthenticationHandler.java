/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.authentication.handler;

/**
 * Offers AuthenticationHandlers a way to identify themselves by a
 * user-configured name.
 *
 * @author Scott Battaglia
 * @version $Revision: 1.1 $ $Date: 2005/08/19 18:27:17 $
 * @since 3.2.1
 */
public interface NamedAuthenticationHandler extends AuthenticationHandler {

    String getName();
}
