/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.services.web;

import com.github.peterchenhdu.future.auth.cas.authentication.principal.Service;
import com.github.peterchenhdu.future.auth.cas.services.RegisteredService;
import com.github.peterchenhdu.future.auth.cas.services.ServicesManager;
import com.github.peterchenhdu.future.auth.cas.web.support.ArgumentExtractor;
import com.github.peterchenhdu.future.auth.cas.web.support.WebUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.theme.AbstractThemeResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.regex.Pattern;

/**
 * ThemeResolver to determine the theme for CAS based on the service provided.
 * The theme resolver will extract the service parameter from the Request object
 * and attempt to match the URL provided to a Service Id. If the service is
 * found, the theme associated with it will be used. If not, these is associated
 * with the service or the service was not found, a default theme will be used.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0
 */
public final class ServiceThemeResolver extends AbstractThemeResolver {

    /**
     * The ServiceRegistry to look up the service.
     */
    private ServicesManager servicesManager;

    private List<ArgumentExtractor> argumentExtractors;

    private Map<Pattern, String> overrides = new HashMap<Pattern, String>();

    public String resolveThemeName(final HttpServletRequest request) {
        if (this.servicesManager == null) {
            return getDefaultThemeName();
        }

        final Service service = WebUtils.getService(this.argumentExtractors, request);

        final RegisteredService rService = this.servicesManager.findServiceBy(service);

        // retrieve the user agent string from the request
        String userAgent = request.getHeader("User-Agent");

        if (userAgent == null) {
            return getDefaultThemeName();
        }

        for (final Map.Entry<Pattern, String> entry : this.overrides.entrySet()) {
            if (entry.getKey().matcher(userAgent).matches()) {
                request.setAttribute("isMobile", "true");
                request.setAttribute("browserType", entry.getValue());
                break;
            }
        }

        return service != null && rService != null && StringUtils.hasText(rService.getTheme()) ? rService.getTheme() : getDefaultThemeName();
    }

    public void setThemeName(final HttpServletRequest request, final HttpServletResponse response, final String themeName) {
        // nothing to do here
    }

    public void setServicesManager(final ServicesManager servicesManager) {
        this.servicesManager = servicesManager;
    }

    public void setArgumentExtractors(final List<ArgumentExtractor> argumentExtractors) {
        this.argumentExtractors = argumentExtractors;
    }

    /**
     * Sets the map of mobile browsers.  This sets a flag on the request called "isMobile" and also
     * provides the custom flag called browserType which can be mapped into the theme.
     * <p>
     * Themes that understand isMobile should provide an alternative stylesheet.
     *
     * @param mobileOverrides the list of mobile browsers.
     */
    public void setMobileBrowsers(final Map<String, String> mobileOverrides) {
        // initialize the overrides variable to an empty map
        this.overrides = new HashMap<Pattern, String>();

        for (final Map.Entry<String, String> entry : mobileOverrides.entrySet()) {
            this.overrides.put(Pattern.compile(entry.getKey()), entry.getValue());
        }
    }
}
