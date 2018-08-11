/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.web.flow;

import com.github.peterchenhdu.future.auth.cas.CentralAuthenticationService;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.Service;
import com.github.peterchenhdu.future.auth.cas.ticket.TicketException;
import com.github.peterchenhdu.future.auth.cas.web.support.WebUtils;
import org.springframework.util.StringUtils;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import javax.validation.constraints.NotNull;

/**
 * Action to generate a service ticket for a given Ticket Granting Ticket and
 * Service.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0.4
 */
public final class GenerateServiceTicketAction extends AbstractAction {

    /**
     * Instance of CentralAuthenticationService.
     */
    @NotNull
    private CentralAuthenticationService centralAuthenticationService;

    protected Event doExecute(final RequestContext context) {
        final Service service = WebUtils.getService(context);
        final String ticketGrantingTicket = WebUtils.getTicketGrantingTicketId(context);

        try {
            final String serviceTicketId = this.centralAuthenticationService
                    .grantServiceTicket(ticketGrantingTicket,
                            service);
            WebUtils.putServiceTicketInRequestScope(context,
                    serviceTicketId);
            return success();
        } catch (final TicketException e) {
            if (isGatewayPresent(context)) {
                return result("gateway");
            }
        }

        return error();
    }

    public void setCentralAuthenticationService(
            final CentralAuthenticationService centralAuthenticationService) {
        this.centralAuthenticationService = centralAuthenticationService;
    }

    protected boolean isGatewayPresent(final RequestContext context) {
        return StringUtils.hasText(context.getExternalContext()
                .getRequestParameterMap().get("gateway"));
    }
}
