/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.services.web.support;

import com.github.peterchenhdu.future.auth.cas.services.RegisteredService;
import com.github.peterchenhdu.future.auth.cas.services.ServicesManager;
import org.apache.commons.lang.StringUtils;
import org.jasig.services.persondir.IPersonAttributeDao;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * RegisteredServiceValidator ensures that a new RegisteredService does not have
 * a conflicting Service Id with another service already in the registry.
 * 
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1
 */
public final class RegisteredServiceValidator implements Validator {

    /** Default length, which matches what is in the view. */
    private static final int DEFAULT_MAX_DESCRIPTION_LENGTH = 300;

    /** {@link ServicesManager} to look up services. */
    @NotNull
    private ServicesManager servicesManager;

    /** The maximum length of the description we will accept. */
    @Min(0)
    private int maxDescriptionLength = DEFAULT_MAX_DESCRIPTION_LENGTH;

    /** {@link IPersonAttributeDao} to manage person attributes */
    @NotNull
    private IPersonAttributeDao personAttributeDao;

    /**
     * Supports {@link RegisteredService} objects.
     * 
     * @see org.springframework.validation.Validator#supports(Class)
     */
    public boolean supports(final Class<?> clazz) {
        return RegisteredService.class.isAssignableFrom(clazz);
    }

    public void validate(final Object o, final Errors errors) {
        final RegisteredService r = (RegisteredService) o;

        if (r.getServiceId() != null) {
            for (final RegisteredService service : this.servicesManager.getAllServices()) {
                if (r.getServiceId().equals(service.getServiceId())
                    && r.getId() != service.getId()) {
                    errors.rejectValue("serviceId",
                        "registeredService.serviceId.exists", null);
                    break;
                }
            }
        }

        if (r.getDescription() != null
            && r.getDescription().length() > this.maxDescriptionLength) {
            errors.rejectValue("description",
                "registeredService.description.length", null);
        }
        
        if (!StringUtils.isBlank(r.getUsernameAttribute()) && !r.isAnonymousAccess()) {
            if (!r.isIgnoreAttributes() && !r.getAllowedAttributes().contains(r.getUsernameAttribute())) {
                errors.rejectValue("usernameAttribute", "registeredService.usernameAttribute.notAvailable",
                        "This attribute is not available for this service.");
            } else {
                Set<String> availableAttributes = this.personAttributeDao.getPossibleUserAttributeNames();
                if (availableAttributes != null) {
                    if (!availableAttributes.contains(r.getUsernameAttribute())) {
                        errors.rejectValue("usernameAttribute", "registeredService.usernameAttribute.notAvailable",
                                "This attribute is not available from configured user attribute sources.");
                    }
                }
            }
        }
    }
    
    public void setServicesManager(final ServicesManager serviceRegistry) {
        this.servicesManager = serviceRegistry;
    }

    public void setMaxDescriptionLength(final int maxLength) {
        this.maxDescriptionLength = maxLength;
    }
    
    public void setPersonAttributeDao(IPersonAttributeDao personAttributeDao) {
        this.personAttributeDao = personAttributeDao;
    }
}
