/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas.services.jmx;

import com.github.peterchenhdu.future.auth.cas.services.ReloadableServicesManager;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * Provides capabilities to reload a {@link com.github.peterchenhdu.future.auth.cas.services.ReloadableServicesManager} from JMX.
 * <p>
 * You should only expose either this class or the {@link com.github.peterchenhdu.future.auth.cas.services.jmx.ServicesManagerMBean}, but not both.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.4.4
 */
@ManagedResource(objectName = "CAS:name=JasigCasServicesManagerMBean",
        description = "Exposes the services management tool via JMX", log = true, logFile="jasig_cas_jmx.log",
        currencyTimeLimit = 15)
public final class ReloadableServicesManagerMBean extends AbstractServicesManagerMBean<ReloadableServicesManager> {

    public ReloadableServicesManagerMBean(final ReloadableServicesManager reloadableServicesManager) {
        super(reloadableServicesManager);
    }

    @ManagedOperation(description = "Reloads the list of the services from the persistence storage.")
    public void reload() {
        getServicesManager().reload();
    }
}
