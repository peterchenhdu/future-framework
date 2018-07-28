/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.web.support;

import javax.servlet.http.HttpServletRequest;

/**
 * Attempts to throttle by both IP Address and username.  Protects against instances where there is a NAT, such as
 * a local campus wireless network.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.3.5
 */
public final class InMemoryThrottledSubmissionByIpAddressAndUsernameHandlerInterceptorAdapter extends AbstractInMemoryThrottledSubmissionHandlerInterceptorAdapter {

    @Override
    protected String constructKey(final HttpServletRequest request) {
        final String username = request.getParameter(getUsernameParameter());

        if (username == null) {
            return request.getRemoteAddr();
        }

        return request.getRemoteAddr() + ";" + username.toLowerCase();
    }
}
