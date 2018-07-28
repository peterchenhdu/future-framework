/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.authentication.principal;

import org.apache.commons.codec.binary.Base64;

import javax.validation.constraints.NotNull;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Generates PersistentIds based on the Shibboleth algorithm.
 * 
 * @author Scott Battaglia
 * @version $Revision: 1.1 $ $Date: 2007/04/20 19:39:31 $
 * @since 3.1
 */
public final class ShibbolethCompatiblePersistentIdGenerator implements
    PersistentIdGenerator {

    private static final byte CONST_SEPARATOR = (byte) '!';
    
    @NotNull
    private byte[] salt;

    public String generate(final Principal principal, final Service service) {
        try {
            final MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(service.getId().getBytes());
            md.update(CONST_SEPARATOR);
            md.update(principal.getId().getBytes());
            md.update(CONST_SEPARATOR);

            return Base64.encodeBase64String(md.digest(this.salt)).replaceAll(
                System.getProperty("line.separator"), "");
        } catch (final NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSalt(final String salt) {
        this.salt = salt.getBytes();
    }
}
