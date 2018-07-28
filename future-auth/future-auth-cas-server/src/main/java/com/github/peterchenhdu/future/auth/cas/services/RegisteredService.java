/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.services;

import com.github.peterchenhdu.future.auth.cas.authentication.principal.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Interface for a service that can be registered by the Services Management
 * interface.
 * 
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1
 */
public interface RegisteredService extends Cloneable, Serializable {    
    /**
     * Is this application currently allowed to use CAS?
     * 
     * @return true if it can use CAS, false otherwise.
     */
    boolean isEnabled();

    /**
     * Determines whether the service is allowed anonymous or privileged access
     * to user information. Anonymous access should not return any identifying
     * information such as user id.
     *
     * @return if we should use a pseudo random identifier instead of their real id
     */
    boolean isAnonymousAccess();
    
    /**
     * Sets whether we should bother to read the attribute list or not.
     * 
     * @return true if we should read it, false otherwise.
     */
    boolean isIgnoreAttributes();

    /**
     * Returns the list of allowed attributes.
     * 
     * @return the list of attributes
     */
    List<String> getAllowedAttributes();

    /**
     * Is this application allowed to take part in the proxying capabilities of
     * CAS?
     * 
     * @return true if it can, false otherwise.
     */
    boolean isAllowedToProxy();

    /**
     * The unique identifier for this service.
     * 
     * @return the unique identifier for this service.
     */
    String getServiceId();

    /**
     * The numeric identifier for this service.
     * 
     * @return the numeric identifier for this service.
     */
    long getId();

    /**
     * Returns the name of the service.
     * 
     * @return the name of the service.
     */
    String getName();

    /**
     * Returns a short theme name. Services do not need to have unique theme
     * names.
     * 
     * @return the theme name associated with this service.
     */
    String getTheme();

    /**
     * Does this application participate in the SSO session?
     * 
     * @return true if it does, false otherwise.
     */
    boolean isSsoEnabled();

    /**
     * Returns the description of the service.
     * 
     * @return the description of the service.
     */
    String getDescription();
   
    /**
     * Gets the relative evaluation order of this service when determining
     * matches.
     * @return Evaluation order relative to other registered services.
     * Services with lower values will be evaluated for a match before others.
     */
    int getEvaluationOrder();

    /**
     * Sets the relative evaluation order of this service when determining
     * matches.
     */
    void setEvaluationOrder(final int evaluationOrder);
    
    /**
     * Get the name of the attribute this service prefers to consume as username.
     * 
     * @return Either of the following values:
     * <ul>
     *  <li><code>String</code> representing the name of the attribute to consume as username</li>
     *  <li><code>null</code> indicating the default username</li>
     * </ul>
     */
    public String getUsernameAttribute();
    
    /**
     * Returns whether the service matches the registered service.
     * <p>Note, as of 3.1.2, matches are case insensitive.
     * 
     * @param service the service to match.
     * @return true if they match, false otherwise.
     */
    boolean matches(final Service service);
    
    Object clone() throws CloneNotSupportedException;
}
