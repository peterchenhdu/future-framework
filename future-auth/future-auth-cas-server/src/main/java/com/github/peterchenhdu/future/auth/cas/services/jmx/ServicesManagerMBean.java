/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas.services.jmx;

import com.github.peterchenhdu.future.auth.cas.services.ServicesManager;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * Supports the basic {@link com.github.peterchenhdu.future.auth.cas.services.ServicesManager}.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.4.4
 */

@ManagedResource(objectName = "CAS:name=JasigCasServicesManagerMBean",
        description = "Exposes the services management tool via JMX", log = true, logFile="jasig_cas_jmx.log",
        currencyTimeLimit = 15)
public final class ServicesManagerMBean extends AbstractServicesManagerMBean<ServicesManager> {

    public ServicesManagerMBean(ServicesManager servicesManager) {
        super(servicesManager);
    }
}
