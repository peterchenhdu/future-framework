/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas;

import com.github.peterchenhdu.future.auth.cas.authentication.principal.SimpleWebApplicationServiceImpl;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.UsernamePasswordCredentials;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chenpi
 * @since 2018/7/28 23:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CentralAuthenticationServiceImplTest {

    @Autowired
    private CentralAuthenticationService centralAuthenticationService;

    @Test
    public void createTicketGrantingTicket() throws Exception {
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials();
        credentials.setUsername("test");
        credentials.setPassword("test");
        String tgt = centralAuthenticationService.createTicketGrantingTicket(credentials);
        System.out.println("TGT:" + tgt);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("service", "http://127.0.0.1/home");
        SimpleWebApplicationServiceImpl service = SimpleWebApplicationServiceImpl.createServiceFrom(request);
        String st = centralAuthenticationService.grantServiceTicket(tgt, service);
        System.out.println("st:" + st);
    }



}