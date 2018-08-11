/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.web;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/**
 * Maps requests for usernames to a page that displays the Login URL for an
 * OpenId Identity Provider.
 *
 * @author Scott Battaglia
 * @version $Revision: 1.1 $ $Date: 2005/08/19 18:27:17 $
 * @since 3.1
 */
public final class OpenIdProviderController extends AbstractController {

    @NotNull
    private String loginUrl;

    protected ModelAndView handleRequestInternal(final HttpServletRequest request,
                                                 final HttpServletResponse response) throws Exception {
        return new ModelAndView("openIdProviderView", "openid_server", this.loginUrl);
    }

    public void setLoginUrl(final String loginUrl) {
        this.loginUrl = loginUrl;
    }
}
