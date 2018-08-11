/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.web.view;

import com.github.peterchenhdu.future.auth.cas.validation.Assertion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;

import java.util.Map;

/**
 * Abstract class to handle retrieving the Assertion from the model.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1
 */
public abstract class AbstractCasView extends AbstractView {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    protected final Assertion getAssertionFrom(final Map<String, Object> model) {
        return (Assertion) model.get("assertion");
    }
}
