/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whnm.sicqfdp.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author wilson
 */
public class WebCqfdpAuthException extends AuthenticationException{
    private static final long serialVersionUID = 1L;

    public WebCqfdpAuthException(String msg) {
        super(msg);
    }
}
