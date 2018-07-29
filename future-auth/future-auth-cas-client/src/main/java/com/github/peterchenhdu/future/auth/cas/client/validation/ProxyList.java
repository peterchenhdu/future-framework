/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas.client.validation;

import com.github.peterchenhdu.future.auth.cas.client.util.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * Holding class for the proxy list to make Spring configuration easier.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1.3
 */
public final class ProxyList {

    private final List<String[]> proxyChains;

    public ProxyList(final List<String[]> proxyChains) {
        CommonUtils.assertNotNull(proxyChains, "List of proxy chains cannot be null.");
        this.proxyChains = proxyChains;
    }

    public ProxyList() {
        this(new ArrayList<String[]>());
    }

    public boolean contains(String[] proxiedList) {
        for (final String[] list : this.proxyChains) {
            if (Arrays.equals(proxiedList, list)) {
                return true;
            }
        }

        return false;
    }
    
    public String toString() {
    	return this.proxyChains.toString();
    }
}
