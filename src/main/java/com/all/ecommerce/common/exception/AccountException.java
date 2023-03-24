package com.all.ecommerce.common.exception;

import com.all.ecommerce.enums.StatusCode;
import org.apache.commons.lang3.StringUtils;

public class AccountException extends UnFillStackTraceException{
    private static final long serialVersionUID = -1043498038361659805L;
    private final StatusCode statusCode;
    public AccountException(StatusCode statusCode){
        this.statusCode = statusCode;
    }
    public AccountException(StatusCode statusCode,String message){
        super(message);
        this.statusCode = statusCode;
    }
    public StatusCode getStatusCode() {

        return this.statusCode;

    }
    @Override
    public String getMessage() {
        return StringUtils.defaultIfBlank(super.getMessage(),statusCode.defaultMessage);
    }
}
