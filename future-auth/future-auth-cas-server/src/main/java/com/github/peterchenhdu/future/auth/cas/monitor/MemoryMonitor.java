/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.monitor;

/**
 * Monitors JVM memory usage.
 *
 * @author Marvin S. Addison
 * @since 3.5.0
 */
public class MemoryMonitor implements Monitor<MemoryStatus> {
   
    /** Default percent free memory warning threshold. */
    public static final int DEFAULT_FREE_MEMORY_WARN_THRESHOLD = 10;
   
    /** Percent free memory warning threshold. */
    private long freeMemoryWarnThreshold = DEFAULT_FREE_MEMORY_WARN_THRESHOLD;


    /**
     * Sets the percent of free memory below which a warning is reported.
     *
     * @param threshold Percent free memory warning threshold.
     */
    public void setFreeMemoryWarnThreshold(final long threshold) {
        if (threshold < 0) {
            throw new IllegalArgumentException("Warning threshold must be non-negative.");
        }
        this.freeMemoryWarnThreshold = threshold;
    }


    /** {@inheritDoc} */
    public String getName() {
        return MemoryMonitor.class.getSimpleName();
    }


    /** {@inheritDoc} */
    public MemoryStatus observe() {
        final StatusCode code;
        final long free = Runtime.getRuntime().freeMemory();
        final long total = Runtime.getRuntime().totalMemory();
        if (free * 100 / total < this.freeMemoryWarnThreshold) {
            code = StatusCode.WARN;
        } else {
            code = StatusCode.OK;
        }
        return new MemoryStatus(code, free, total);
    }
}
