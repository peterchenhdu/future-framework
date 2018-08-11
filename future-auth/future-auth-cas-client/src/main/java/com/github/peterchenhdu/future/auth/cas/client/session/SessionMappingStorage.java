/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.auth.cas.client.session;

import javax.servlet.http.HttpSession;

/**
 * Stores the mapping between sessions and keys to be retrieved later.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1
 */
public interface SessionMappingStorage {

    /**
     * Remove the HttpSession based on the mappingId.
     *
     * @param mappingId the id the session is keyed under.
     * @return the HttpSession if it exists.
     */
    HttpSession removeSessionByMappingId(String mappingId);

    /**
     * Remove a session by its Id.
     *
     * @param sessionId the id of the session.
     */
    void removeBySessionById(String sessionId);

    /**
     * Add a session by its mapping Id.
     *
     * @param mappingId the id to map the session to.
     * @param session   the HttpSession.
     */
    void addSessionById(String mappingId, HttpSession session);

}
