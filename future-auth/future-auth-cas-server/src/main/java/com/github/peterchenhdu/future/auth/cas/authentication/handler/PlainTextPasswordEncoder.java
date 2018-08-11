/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.authentication.handler;

/**
 * Default password encoder for the case where no password encoder is needed.
 * Encoding results in the same password that was passed in.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0
 */
public final class PlainTextPasswordEncoder implements PasswordEncoder {

    public String encode(final String password) {
        return password;
    }
}
