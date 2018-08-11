/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas.audit.spi;

import com.github.inspektr.audit.spi.AuditResourceResolver;
import com.github.peterchenhdu.future.auth.cas.util.AopUtils;
import org.aspectj.lang.JoinPoint;

/**
 * Resolves a service id to the service.
 * <p>
 * The expectation is that args[0] is a Long.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.4.6
 */
public final class ServiceManagementResourceResolver implements AuditResourceResolver {

    public String[] resolveFrom(final JoinPoint target, final Object returnValue) {
        return findService(target);
    }

    public String[] resolveFrom(final JoinPoint target, final Exception exception) {
        return findService(target);
    }

    private String[] findService(final JoinPoint joinPoint) {
        final JoinPoint j = AopUtils.unWrapJoinPoint(joinPoint);

        final Long id = (Long) j.getArgs()[0];

        if (id == null) {
            return new String[]{""};
        }

        return new String[]{"id=" + id};
    }
}
