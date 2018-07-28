/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.validation;

/**
 * Validation specification for the CAS 2.0 protocol. This specification extends
 * the Cas10ProtocolValidationSpecification, checking for the presence of
 * renew=true and if requested, succeeding only if ticket validation is
 * occurring from a new login.
 * 
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0
 */
public class Cas20ProtocolValidationSpecification extends
    AbstractCasProtocolValidationSpecification {

    public Cas20ProtocolValidationSpecification() {
        super();
    }

    public Cas20ProtocolValidationSpecification(final boolean renew) {
        super(renew);
    }

    protected boolean isSatisfiedByInternal(final Assertion assertion) {
        return true;
    }
}
