/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.monitor;

/**
 * A monitor observes a resource and reports its status.
 *
 * @author Marvin S. Addison
 * @since 3.5.0
 */
public interface Monitor<S extends Status> {

    /**
     * Gets the name of the monitor.
     *
     * @return Monitor name.
     */
    String getName();


    /**
     * Observes the monitored resource and reports the status.
     *
     * @return Status of monitored resource.
     */
    S observe();
}
