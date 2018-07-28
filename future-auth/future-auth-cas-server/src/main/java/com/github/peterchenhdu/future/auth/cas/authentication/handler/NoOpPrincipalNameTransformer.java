/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.authentication.handler;

/**
 * Simple implementation that actually does NO transformation.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.3.6
 */
public final class NoOpPrincipalNameTransformer implements PrincipalNameTransformer {

    public String transform(final String formUserId) {
        return formUserId;
    }
}
