/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas;

/**
 * Class that exposes the CAS version. Fetches the "Implementation-Version"
 * manifest attribute from the jar file.
 *
 * @author Dmitriy Kopylenko
 * @version $Revision$ $Date$
 * @since 3.0
 */
public final class CasVersion {

    /**
     * Private constructor for CasVersion. You should not be able to instanciate
     * this class.
     */
    private CasVersion() {
        // this class is not instantiable
    }

    /**
     * Return the full CAS version string.
     * 
     * @see Package#getImplementationVersion
     */
    public static String getVersion() {
        return CasVersion.class.getPackage().getImplementationVersion();
    }
}
