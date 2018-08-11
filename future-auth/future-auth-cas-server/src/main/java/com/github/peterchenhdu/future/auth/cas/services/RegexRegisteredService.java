/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.services;

import com.github.peterchenhdu.future.auth.cas.authentication.principal.Service;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.regex.Pattern;

/**
 * Mutable registered service that uses Java regular expressions for service matching.
 *
 * @author Marvin S. Addison
 * @version $Revision: $
 */
@Entity
@DiscriminatorValue("regex")
public class RegexRegisteredService extends AbstractRegisteredService {
    /**
     * Serialization version marker
     */
    private static final long serialVersionUID = -8258660210826975771L;

    private transient Pattern servicePattern;

    public void setServiceId(final String id) {
        servicePattern = createPattern(id);
        serviceId = id;
    }

    public boolean matches(final Service service) {
        if (servicePattern == null) {
            servicePattern = createPattern(serviceId);
        }
        return service != null && servicePattern.matcher(service.getId()).matches();
    }

    protected AbstractRegisteredService newInstance() {
        return new RegexRegisteredService();
    }

    private Pattern createPattern(final String pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be null.");
        }
        return Pattern.compile(pattern);
    }
}
