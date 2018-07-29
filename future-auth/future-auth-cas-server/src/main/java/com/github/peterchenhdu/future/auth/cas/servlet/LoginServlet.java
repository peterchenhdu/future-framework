/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas.servlet;

import com.github.peterchenhdu.future.auth.cas.CentralAuthenticationService;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.Response;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.Service;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.SimpleWebApplicationServiceImpl;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.UsernamePasswordCredentials;
import com.github.peterchenhdu.future.auth.cas.ticket.TicketException;
import com.github.peterchenhdu.future.auth.cas.ticket.TicketGrantingTicket;
import com.github.peterchenhdu.future.auth.cas.ticket.registry.TicketRegistry;
import com.github.peterchenhdu.future.auth.cas.util.HttpClient;
import com.github.peterchenhdu.future.auth.cas.web.support.ArgumentExtractor;
import com.github.peterchenhdu.future.auth.cas.web.support.CookieRetrievingCookieGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author chenpi
 * @since 2018/7/29 10:08
 */
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CookieRetrievingCookieGenerator ticketGrantingTicketCookieGenerator;
    @Autowired
    private CookieRetrievingCookieGenerator warnCookieGenerator;
    @Autowired
    private List<ArgumentExtractor> argumentExtractors;
    @Autowired
    private CentralAuthenticationService centralAuthenticationService;
    @Autowired
    private HttpClient httpClient;
    @Autowired
    private TicketRegistry ticketRegistry;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("-----------doGet----------------");
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("-----------doPost----------------");
        final String contextPath = getServletContext().getContextPath();
        final String cookiePath = StringUtils.hasText(contextPath) ? contextPath + "/" : "/";
        log.info("Setting path for cookies to: "
                + cookiePath);
        this.warnCookieGenerator.setCookiePath(cookiePath);
        this.ticketGrantingTicketCookieGenerator.setCookiePath(cookiePath);


        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials();
        credentials.setUsername(request.getParameter("name"));
        credentials.setPassword(request.getParameter("password"));


        String ticketGrantingTicketId = this.ticketGrantingTicketCookieGenerator.retrieveCookieValue(request);
        if (org.apache.commons.lang.StringUtils.isBlank(ticketGrantingTicketId)) {
            try {
                ticketGrantingTicketId = centralAuthenticationService.createTicketGrantingTicket(credentials);
                log.info("tgt:" + ticketGrantingTicketId);
                Service service = getServiceFromReq(request, httpClient);
                String serviceTicketId = this.centralAuthenticationService.grantServiceTicket(ticketGrantingTicketId,
                        service, credentials);
                this.ticketGrantingTicketCookieGenerator.addCookie(request, resp, ticketGrantingTicketId);
                log.info("st:" + serviceTicketId);
                resp.sendRedirect(service.getId() + "?ticket=" + serviceTicketId);
            } catch (TicketException e) {
                e.printStackTrace();
            }
        } else {
            resp.setHeader("Content-type", "text/html;charset=UTF-8");
            resp.setCharacterEncoding("utf-8");

            final TicketGrantingTicket ticketGrantingTicket = (TicketGrantingTicket) this.ticketRegistry.getTicket
                    (ticketGrantingTicketId, TicketGrantingTicket.class);
            if (ticketGrantingTicket == null) {
                this.ticketGrantingTicketCookieGenerator.addCookie(request, resp, "");
                resp.getWriter().print("<h6>TGC无效,请重新登录：tgc:" + ticketGrantingTicketId + "</h6>");
            } else {
                resp.getWriter().print("<h6>你已经登录成功：tgc:" + ticketGrantingTicketId + "</h6>");
            }


        }

    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    private static SimpleWebApplicationServiceImpl getServiceFromReq(
            final HttpServletRequest request, final HttpClient httpClient) {
        final String targetService = request.getParameter("targetService");
        final String method = request.getParameter("method");
        String serviceToUse = StringUtils.hasText(targetService)
                ? targetService : request.getParameter("service");

        if (!StringUtils.hasText(serviceToUse)) {
            serviceToUse = "http://localhost:8080/";
        }

        final String id = serviceToUse;
        final String artifactId = request.getParameter("ticket");

        return new SimpleWebApplicationServiceImpl(id, serviceToUse,
                artifactId, "POST".equals(method) ? Response.ResponseType.POST
                : Response.ResponseType.REDIRECT, httpClient);
    }

}
