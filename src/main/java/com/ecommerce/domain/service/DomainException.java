package com.ecommerce.domain.service;
/**
 *
 * @author jfvaldez
 */
public class DomainException extends RuntimeException{
    DomainException(final String message) {
        super(message);
    }
}
