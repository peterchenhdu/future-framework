/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.validation;

/**
 * Validation specification for the CAS 2.0 protocol. This specification extends
 * the Cas20ProtocolValidationSpecification, checking for the presence of
 * renew=true and if requested, succeeding only if ticket validation is
 * occurring from a new login. Additionally, this specification will not accept
 * proxied authentications.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0
 */
public class Cas20WithoutProxyingValidationSpecification extends
        AbstractCasProtocolValidationSpecification {

    public Cas20WithoutProxyingValidationSpecification() {
        super();
    }

    public Cas20WithoutProxyingValidationSpecification(final boolean renew) {
        super(renew);
    }

    protected boolean isSatisfiedByInternal(final Assertion assertion) {
        return (assertion.getChainedAuthentications().size() == 1);
    }
}
