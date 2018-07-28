/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author chenpi
 * @since 2018/7/28 19:53
 */
@Configuration
@ImportResource("classpath:cas-context.xml")
public class CasServerContext {

//    @Bean
//    public AuthenticationManagerImpl authenticationManager(StubPersonAttributeDao attributeRepository,
//                                                           HttpClient httpClient) {
//        AuthenticationManagerImpl authenticationManager = new AuthenticationManagerImpl();
//        List<CredentialsToPrincipalResolver> credentialsToPrincipalResolvers = new ArrayList<>();
//        credentialsToPrincipalResolvers.add(new HttpBasedServiceCredentialsToPrincipalResolver());
//        UsernamePasswordCredentialsToPrincipalResolver upc = new UsernamePasswordCredentialsToPrincipalResolver();
//        upc.setAttributeRepository(attributeRepository);
//        credentialsToPrincipalResolvers.add(upc);
//        authenticationManager.setCredentialsToPrincipalResolvers(credentialsToPrincipalResolvers);
//
//        List<AuthenticationHandler> authenticationHandlers = new ArrayList<>();
//        authenticationHandlers.add(new SimpleTestUsernamePasswordAuthenticationHandler());
//        HttpBasedServiceCredentialsAuthenticationHandler hca = new HttpBasedServiceCredentialsAuthenticationHandler();
//        hca.setHttpClient(httpClient);
//        hca.setRequireSecure(false);
//        authenticationManager.setAuthenticationHandlers(authenticationHandlers);
//        return authenticationManager;
//    }
}
