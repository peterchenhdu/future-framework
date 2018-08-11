/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.services;

import com.github.peterchenhdu.future.auth.cas.authentication.principal.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Mutable registered service that uses Ant path patterns for service matching.
 *
 * @author Scott Battaglia
 * @author Marvin S. Addison
 * @version $Revision$ $Date$
 * @since 3.1
 */
@Entity
@DiscriminatorValue("ant")
public class RegisteredServiceImpl extends AbstractRegisteredService {

    /**
     * Unique Id for serialization.
     */
    private static final long serialVersionUID = -5906102762271197627L;

    private static final PathMatcher PATH_MATCHER = new AntPathMatcher();

    public void setServiceId(final String id) {
        this.serviceId = id;
    }

    public boolean matches(final Service service) {
        return service != null && PATH_MATCHER.match(serviceId.toLowerCase(), service.getId().toLowerCase());
    }

    protected AbstractRegisteredService newInstance() {
        return new RegisteredServiceImpl();
    }
}

