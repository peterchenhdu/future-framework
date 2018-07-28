/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.authentication.principal;

import java.io.Serializable;
import java.util.Map;

/**
 * Generic concept of an authenticated thing. Examples include a person or a
 * service.
 * <p>
 * The implementation SimplePrincipal just contains the Id property. More
 * complex Principal objects may contain additional information that are
 * meaningful to the View layer but are generally transparent to the rest of
 * CAS.
 * </p>
 * 
 * @author Scott Battaglia
 * @version $Revision: 1.3 $ $Date: 2007/04/19 20:13:01 $
 * @since 3.0
 * <p>
 * This is a published and supported CAS Server 3 API.
 * </p>
 */
public interface Principal extends Serializable {

    /**
     * Returns the unique id for the Principal
     * @return the unique id for the Principal.
     */
    String getId();
    
    /**
     * 
     * @return
     */
    Map<String, Object> getAttributes();
}
