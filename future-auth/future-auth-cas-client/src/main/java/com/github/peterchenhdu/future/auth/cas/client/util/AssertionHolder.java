/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas.client.util;

import com.github.peterchenhdu.future.auth.cas.client.validation.Assertion;

/**
 * Static holder that places Assertion in a ThreadLocal.
 *
 * @author Scott Battaglia
 * @version $Revision: 11728 $ $Date: 2007-09-26 14:20:43 -0400 (Tue, 26 Sep 2007) $
 * @since 3.0
 */
public class AssertionHolder {

    /**
     * ThreadLocal to hold the Assertion for Threads to access.
     */
    private static final ThreadLocal<Assertion> threadLocal = new ThreadLocal<Assertion>();


    /**
     * Retrieve the assertion from the ThreadLocal.
     *
     * @return the Asssertion associated with this thread.
     */
    public static Assertion getAssertion() {
        return threadLocal.get();
    }

    /**
     * Add the Assertion to the ThreadLocal.
     *
     * @param assertion the assertion to add.
     */
    public static void setAssertion(final Assertion assertion) {
        threadLocal.set(assertion);
    }

    /**
     * Clear the ThreadLocal.
     */
    public static void clear() {
        threadLocal.set(null);
    }
}
