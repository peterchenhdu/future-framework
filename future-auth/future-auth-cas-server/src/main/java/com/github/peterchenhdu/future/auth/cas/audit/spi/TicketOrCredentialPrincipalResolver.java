///*
// * Copyright (c) 2011-2025 PiChen
// */
//package com.github.peterchenhdu.future.auth.cas.audit.spi;
//
//import org.aspectj.lang.JoinPoint;
//import com.github.inspektr.common.spi.PrincipalResolver;
//import com.github.peterchenhdu.future.auth.cas.authentication.principal.Credentials;
//import com.github.peterchenhdu.future.auth.cas.ticket.ServiceTicket;
//import com.github.peterchenhdu.future.auth.cas.ticket.Ticket;
//import com.github.peterchenhdu.future.auth.cas.ticket.TicketGrantingTicket;
//import com.github.peterchenhdu.future.auth.cas.ticket.registry.TicketRegistry;
//import com.github.peterchenhdu.future.auth.cas.util.AopUtils;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.validation.constraints.NotNull;
//
///**
// * PrincipalResolver that can retrieve the username from either the Ticket or from the Credentials.
// *
// * @author Scott Battaglia
// * @version $Revision: 1.1 $ $Date: 2005/08/19 18:27:17 $
// * @since 3.1.2
// *
// */
//public final class TicketOrCredentialPrincipalResolver implements PrincipalResolver {
//
//    @NotNull
//    private final TicketRegistry ticketRegistry;
//
//    public TicketOrCredentialPrincipalResolver(final TicketRegistry ticketRegistry) {
//        this.ticketRegistry = ticketRegistry;
//    }
//
//    public String resolveFrom(final JoinPoint joinPoint, final Object retVal) {
//        return resolveFromInternal(AopUtils.unWrapJoinPoint(joinPoint));
//    }
//
//    public String resolveFrom(final JoinPoint joinPoint, final Exception retVal) {
//        return resolveFromInternal(AopUtils.unWrapJoinPoint(joinPoint));
//    }
//
//    public String resolve() {
//        return UNKNOWN_USER;
//    }
//
//    protected String resolveFromInternal(final JoinPoint joinPoint) {
//        final Object arg1 = joinPoint.getArgs()[0];
//        if (arg1 instanceof Credentials) {
//           return arg1.toString();
//        } else if (arg1 instanceof String) {
//            final Ticket ticket = this.ticketRegistry.getTicket((String) arg1);
//            if (ticket instanceof ServiceTicket) {
//                final ServiceTicket serviceTicket = (ServiceTicket) ticket;
//                return serviceTicket.getGrantingTicket().getAuthentication().getPrincipal().getId();
//            } else if (ticket instanceof TicketGrantingTicket) {
//                final TicketGrantingTicket tgt = (TicketGrantingTicket) ticket;
//                return tgt.getAuthentication().getPrincipal().getId();
//            }
//        } else {
//            final SecurityContext securityContext = SecurityContextHolder.getContext();
//            if (securityContext != null) {
//                final Authentication authentication = securityContext.getAuthentication();
//
//                if (authentication != null) {
//                    return ((UserDetails) authentication.getPrincipal()).getUsername();
//                }
//            }
//        }
//        return UNKNOWN_USER;
//    }
//}
