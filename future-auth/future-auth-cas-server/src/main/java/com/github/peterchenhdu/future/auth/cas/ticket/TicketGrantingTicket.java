/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.ticket;

import com.github.peterchenhdu.future.auth.cas.authentication.Authentication;
import com.github.peterchenhdu.future.auth.cas.authentication.principal.Service;

import java.util.List;

/**
 * Interface for a ticket granting ticket. A TicketGrantingTicket is the main
 * access into the CAS service layer. Without a TicketGrantingTicket, a user of
 * CAS cannot do anything.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0
 */
public interface TicketGrantingTicket extends Ticket {

    /**
     * The prefix to use when generating an id for a TicketGrantingTicket.
     */
    String PREFIX = "TGT";

    /**
     * Method to retrieve the authentication.
     *
     * @return the authentication
     */
    Authentication getAuthentication();

    /**
     * Grant a ServiceTicket for a specific service.
     *
     * @param id      The unique identifier for this ticket.
     * @param service The service for which we are granting a ticket
     * @return the service ticket granted to a specific service for the
     * principal of the TicketGrantingTicket
     */
    ServiceTicket grantServiceTicket(String id, Service service,
                                     ExpirationPolicy expirationPolicy, boolean credentialsProvided);

    /**
     * Explicitly expire a ticket.  This method will log out of any service associated with the
     * Ticket Granting Ticket.
     */
    void expire();

    /**
     * Convenience method to determine if the TicketGrantingTicket is the root
     * of the hierarchy of tickets.
     *
     * @return true if it has no parent, false otherwise.
     */
    boolean isRoot();

    /**
     * Method to retrieve the chained list of Authentications for this
     * TicketGrantingTicket.
     *
     * @return the list of principals
     */
    List<Authentication> getChainedAuthentications();

    /**
     * Gets the service that produced a proxy-granting ticket.
     *
     * @return Service that produced proxy-granting ticket or null if this is
     * not a proxy-granting ticket.
     */
    String getProxiedBy();
}
