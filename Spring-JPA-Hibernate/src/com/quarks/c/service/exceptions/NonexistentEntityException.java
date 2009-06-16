package com.quarks.c.service.exceptions;
/**
 * 
 * @author Kuldeep Singh
 *
 */
public class NonexistentEntityException extends Exception {
    public NonexistentEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    public NonexistentEntityException(String message) {
        super(message);
    }
}
