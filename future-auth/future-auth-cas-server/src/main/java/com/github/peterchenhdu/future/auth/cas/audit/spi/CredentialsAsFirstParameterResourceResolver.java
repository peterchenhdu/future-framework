/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.audit.spi;

import org.aspectj.lang.JoinPoint;
import com.github.inspektr.audit.spi.AuditResourceResolver;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.Credentials;
import com.github.peterchenhdu.future.auth.cas.util.AopUtils;

/**
 * Converts the Credentials object into a String resource identifier.
 * 
 * @author Scott Battaglia
 * @version $Revision: 1.1 $ $Date: 2005/08/19 18:27:17 $
 * @since 3.1.2
 *
 */
public final class CredentialsAsFirstParameterResourceResolver implements AuditResourceResolver {

    public String[] resolveFrom(final JoinPoint joinPoint, final Object retval) {
        final Credentials credentials = (Credentials) AopUtils.unWrapJoinPoint(joinPoint).getArgs()[0];
        return new String[] { "supplied credentials: " + credentials.toString() };
    }

    public String[] resolveFrom(final JoinPoint joinPoint, final Exception exception) {
        final Credentials credentials = (Credentials) AopUtils.unWrapJoinPoint(joinPoint).getArgs()[0];
        return new String[] { "supplied credentials: " + credentials.toString() };
    }
}
