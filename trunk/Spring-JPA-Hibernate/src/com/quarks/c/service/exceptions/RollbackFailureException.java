package com.quarks.c.service.exceptions;
/**
 * 
 * @author Kuldeep Singh
 *
 */
public class RollbackFailureException extends Exception {
    public RollbackFailureException(String message, Throwable cause) {
        super(message, cause);
    }
    public RollbackFailureException(String message) {
        super(message);
    }
}
