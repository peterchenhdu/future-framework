/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.monitor;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

/**
 * Base class for all monitors that support configurable naming.
 *
 * @author Marvin S. Addison
 * @since 3.5.0
 */
public abstract class AbstractNamedMonitor<S extends Status> implements Monitor<S> {
    /**
     * Monitor name.
     */
    protected String name;


    /**
     * @return Monitor name.
     */
    public String getName() {
        return StringUtils.defaultIfEmpty(this.name, getClass().getSimpleName());
    }

    /**
     * @param n Monitor name.
     */
    public void setName(final String n) {
        Assert.hasText(n, "Monitor name cannot be null or empty.");
        this.name = n;
    }
}
