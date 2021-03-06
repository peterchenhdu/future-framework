/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.auth.cas.authentication.handler;

/**
 * The most generic type of authentication exception that one can catch if not
 * sure what specific implementation will be thrown. Top of the tree of all
 * other AuthenticationExceptions.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.0
 */
public abstract class AuthenticationException extends Exception {

    /**
     * Serializable ID.
     */
    private static final long serialVersionUID = 3906648604830611762L;

    /**
     * The code to return for resolving to a message description.
     */
    private String code;

    /**
     * The error type that provides additional info about the nature of the exception cause
     **/
    private String type = "error";

    /**
     * Constructor that takes a code description of the error. These codes
     * normally have a corresponding entries in the messages file for the
     * internationalization of error messages.
     *
     * @param code The short unique identifier for this error.
     */
    public AuthenticationException(final String code) {
        this.code = code;
    }

    /**
     * Constructor that takes a <code>code</code> description of the error along with the exception
     * <code>msg</code> generally for logging purposes. These codes normally have a corresponding
     * entries in the messages file for the internationalization of error messages.
     *
     * @param code The short unique identifier for this error.
     * @param msg  The error message associated with this exception for additional logging purposes.
     */
    public AuthenticationException(final String code, final String msg) {
        super(msg);
        this.code = code;
    }

    /**
     * Constructor that takes a <code>code</code> description of the error along with the exception
     * <code>msg</code> generally for logging purposes and the <code>type</code> of the error that originally caused the exception.
     * These codes normally have a corresponding entries in the messages file for the internationalization of error messages.
     *
     * @param code The short unique identifier for this error.
     * @param msg  The error message associated with this exception for additional logging purposes.
     * @param type The type of the error message that caused the exception to be thrown. By default,
     *             all errors are considered of <code>error</code>.
     */
    public AuthenticationException(final String code, final String msg, final String type) {
        super(msg);
        this.code = code;
        this.type = type;
    }

    /**
     * Constructor that takes a code description of the error and the chained
     * exception. These codes normally have a corresponding entries in the
     * messages file for the internationalization of error messages.
     *
     * @param code      The short unique identifier for this error.
     * @param throwable The chained exception for this AuthenticationException
     */
    public AuthenticationException(final String code, final Throwable throwable) {
        super(throwable);
        this.code = code;
    }

    /**
     * Method to return the error type of this exception
     *
     * @return the String identifier for the cause of this error.
     */
    public final String getType() {
        return this.type;
    }

    /**
     * Method to return the unique identifier for this error type.
     *
     * @return the String identifier for this error type.
     */
    public final String getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        String msg = getCode();
        if (getMessage() != null && getMessage().trim().length() > 0)
            msg = ":" + getMessage();
        return msg;
    }

}
