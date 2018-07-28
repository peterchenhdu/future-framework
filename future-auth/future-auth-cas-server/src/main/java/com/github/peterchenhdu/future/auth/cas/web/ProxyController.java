/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.web;

import com.github.peterchenhdu.future.auth.cas.CentralAuthenticationService;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.Service;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.SimpleWebApplicationServiceImpl;
import com.github.peterchenhdu.future.auth.cas.services.UnauthorizedServiceException;
import com.github.peterchenhdu.future.auth.cas.ticket.TicketException;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/**
 * The ProxyController is involved with returning a Proxy Ticket (in CAS 2
 * terms) to the calling application. In CAS 3, a Proxy Ticket is just a Service
 * Ticket granted to a service.
 * <p>
 * The ProxyController requires the following property to be set:
 * </p>
 * <ul>
 * <li> centralAuthenticationService - the service layer</li>
 * <li> casArgumentExtractor - the assistant for extracting parameters</li>
 * </ul>
 * 
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0
 */
public final class ProxyController extends AbstractController {

    /** View for if the creation of a "Proxy" Ticket Fails. */
    private static final String CONST_PROXY_FAILURE = "casProxyFailureView";

    /** View for if the creation of a "Proxy" Ticket Succeeds. */
    private static final String CONST_PROXY_SUCCESS = "casProxySuccessView";

    /** Key to use in model for service tickets. */
    private static final String MODEL_SERVICE_TICKET = "ticket";

    /** CORE to delegate all non-web tier functionality to. */
    @NotNull
    private CentralAuthenticationService centralAuthenticationService;

    public ProxyController() {
        setCacheSeconds(0);
    }

    /**
     * @return ModelAndView containing a view name of either
     * <code>casProxyFailureView</code> or <code>casProxySuccessView</code>
     */
    protected ModelAndView handleRequestInternal(
        final HttpServletRequest request, final HttpServletResponse response)
        throws Exception {
        final String ticket = request.getParameter("pgt");
        final Service targetService = getTargetService(request);

        if (!StringUtils.hasText(ticket) || targetService == null) {
            return generateErrorView("INVALID_REQUEST",
                "INVALID_REQUEST_PROXY", null);
        }

        try {
            return new ModelAndView(CONST_PROXY_SUCCESS, MODEL_SERVICE_TICKET,
                this.centralAuthenticationService.grantServiceTicket(ticket,
                    targetService));
        } catch (TicketException e) {
            return generateErrorView(e.getCode(), e.getCode(),
                new Object[] {ticket});
        } catch (final UnauthorizedServiceException e) {
            return generateErrorView("UNAUTHORIZED_SERVICE",
                "UNAUTHORIZED_SERVICE_PROXY", new Object[] {targetService});
        }
    }

    private Service getTargetService(final HttpServletRequest request) {
        return SimpleWebApplicationServiceImpl.createServiceFrom(request);
    }

    private ModelAndView generateErrorView(final String code,
        final String description, final Object[] args) {
        final ModelAndView modelAndView = new ModelAndView(CONST_PROXY_FAILURE);
        modelAndView.addObject("code", code);
        modelAndView.addObject("description", getMessageSourceAccessor()
            .getMessage(description, args, description));

        return modelAndView;
    }

    /**
     * @param centralAuthenticationService The centralAuthenticationService to
     * set.
     */
    public void setCentralAuthenticationService(
        final CentralAuthenticationService centralAuthenticationService) {
        this.centralAuthenticationService = centralAuthenticationService;
    }
}
