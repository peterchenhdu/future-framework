/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.util;

/**
 * Interface to return a random String.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0
 */
public interface RandomStringGenerator {

    /**
     * @return the minimum length as an int guaranteed by this generator.
     */
    int getMinLength();

    /**
     * @return the maximum length as an int guaranteed by this generator.
     */
    int getMaxLength();

    /**
     * @return the new random string
     */
    String getNewString();

    byte[] getNewStringAsBytes();
}
