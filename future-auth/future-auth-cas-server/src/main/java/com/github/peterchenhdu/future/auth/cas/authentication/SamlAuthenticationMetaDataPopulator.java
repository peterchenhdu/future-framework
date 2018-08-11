/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.authentication;

import com.github.peterchenhdu.future.auth.cas.authentication.principal.Credentials;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.HttpBasedServiceCredentials;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.UsernamePasswordCredentials;

import java.util.HashMap;
import java.util.Map;

/**
 * AuthenticationMetaDataPopulator to retrieve the Authentication Type.
 * <p>
 * Note: Authentication Methods are exposed under the key:
 * <code>samlAuthenticationStatement::authMethod</code> in the Authentication
 * attributes map.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1
 */
public class SamlAuthenticationMetaDataPopulator implements
        AuthenticationMetaDataPopulator {

    public static final String ATTRIBUTE_AUTHENTICATION_METHOD = "samlAuthenticationStatementAuthMethod";

    public static final String AUTHN_METHOD_PASSWORD = "urn:oasis:names:tc:SAML:1.0:am:password";

    public static final String AUTHN_METHOD_SSL_TLS_CLIENT = "urn:ietf:rfc:2246";

    public static final String AUTHN_METHOD_X509_PUBLICKEY = "urn:oasis:names:tc:SAML:1.0:am:X509-PKI";

    public static final String AUTHN_METHOD_UNSPECIFIED = "urn:oasis:names:tc:SAML:1.0:am:unspecified";

    private final Map<String, String> authenticationMethods = new HashMap<String, String>();

    public SamlAuthenticationMetaDataPopulator() {
        this.authenticationMethods.put(
                HttpBasedServiceCredentials.class.getName(),
                AUTHN_METHOD_SSL_TLS_CLIENT);
        this.authenticationMethods.put(
                UsernamePasswordCredentials.class.getName(),
                AUTHN_METHOD_PASSWORD);

        // Next two classes are in other modules, so avoid using Class#getName() to prevent circular dependency
        this.authenticationMethods.put(
                "com.github.peterchenhdu.future.auth.cas.adaptors.trusted.authentication.principal.PrincipalBearingCredentials",
                AUTHN_METHOD_UNSPECIFIED);
        this.authenticationMethods.put(
                "com.github.peterchenhdu.future.auth.cas.adaptors.x509.authentication.principal.X509CertificateCredentials",
                AUTHN_METHOD_X509_PUBLICKEY);
    }

    public final Authentication populateAttributes(final Authentication authentication, final Credentials credentials) {

        final String credentialsClass = credentials.getClass().getName();
        final String authenticationMethod = this.authenticationMethods.get(credentialsClass);

        authentication.getAttributes().put(ATTRIBUTE_AUTHENTICATION_METHOD, authenticationMethod);

        return authentication;
    }

    /**
     * Map of user-defined mappings. Note it is possible to over-ride the
     * defaults. Mapping should be of the following type:
     * <p>(<String version of Package/Class Name> <SAML Type>)
     * <p>
     * Example: (<"com.github.peterchenhdu.future.auth.cas.authentication.principal.HttpBasedServiceCredentials">
     * <SAMLAuthenticationStatement.AuthenticationMethod_SSL_TLS_Client>)
     *
     * @param userDefinedMappings map of user defined authentication types.
     */
    public void setUserDefinedMappings(final Map<String, String> userDefinedMappings) {
        this.authenticationMethods.putAll(userDefinedMappings);
    }
}
