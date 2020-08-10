package com.IDEXX.animana.assessment.model;

import org.springframework.http.HttpStatus;

/*
 * This class populates additional details in the error response.
 *
 */

public class RestErrorInfo {
    public final String detail;
    public final String message;
    public final HttpStatus status;

    public RestErrorInfo(Exception ex, String message , String detail, HttpStatus status) {
    	super();
        this.message = message;
        this.detail = detail;
        this.status = status;
    }
}
