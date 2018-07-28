/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.web.flow;

import com.github.peterchenhdu.future.auth.cas.util.UniqueTicketIdGenerator;
import com.github.peterchenhdu.future.auth.cas.web.support.WebUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.webflow.execution.RequestContext;

import javax.validation.constraints.NotNull;


/**
 * Generates the login ticket parameter as described in section 3.5 of the
 * <a href="http://www.jasig.org/cas/protocol">CAS protocol</a>.
 *
 * @author Marvin S. Addison
 * @version $Revision$ $Date$
 * @since 3.4.9
 *
 */
public class GenerateLoginTicketAction {
    /** 3.5.1 - Login tickets SHOULD begin with characters "LT-" */
    private static final String PREFIX = "LT";

    /** Logger instance */
    private final Log logger = LogFactory.getLog(getClass());


    @NotNull
    private UniqueTicketIdGenerator ticketIdGenerator;

    public final String generate(final RequestContext context) {
        final String loginTicket = this.ticketIdGenerator.getNewTicketId(PREFIX);
        this.logger.debug("Generated login ticket " + loginTicket);
        WebUtils.putLoginTicket(context, loginTicket);
        return "generated";
    }

    public void setTicketIdGenerator(final UniqueTicketIdGenerator generator) {
        this.ticketIdGenerator = generator;
    }
}
