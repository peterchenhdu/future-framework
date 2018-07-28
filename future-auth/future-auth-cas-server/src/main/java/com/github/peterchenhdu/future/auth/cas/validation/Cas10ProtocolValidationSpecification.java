/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.validation;

/**
 * Validation specification for the CAS 1.0 protocol. This specification checks
 * for the presence of renew=true and if requested, succeeds only if ticket
 * validation is occurring from a new login. Additionally, validation will fail
 * if passed a proxy ticket.
 * 
 * @author Scott Battaglia
 * @author Drew Mazurek
 * @version $Revision$ $Date$
 * @since 3.0
 */
public final class Cas10ProtocolValidationSpecification extends
    AbstractCasProtocolValidationSpecification {

    public Cas10ProtocolValidationSpecification() {
        super();
    }

    public Cas10ProtocolValidationSpecification(final boolean renew) {
        super(renew);
    }

    protected boolean isSatisfiedByInternal(final Assertion assertion) {
        return (assertion.getChainedAuthentications().size() == 1);
    }
}
