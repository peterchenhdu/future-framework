/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.util;

/**
 * Interface to guaranteed to return a long.
 * 
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0
 */
public interface LongNumericGenerator extends NumericGenerator {

    /**
     * Get the next long in the sequence.
     * 
     * @return the next long in the sequence.
     */
    long getNextLong();
}
