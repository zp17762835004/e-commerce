package com.all.ecommerce.enums;

public enum StatusCode {
    BAD_AUTHENTICATION(400001,"用户名不存在"),
    NOT_SUFFICIENT_FUNDS(500002,"账户余额不足");
    public final int code;

    public final String defaultMessage;

    StatusCode(int code, String defaultMessage) {

        this.code = code;

        this.defaultMessage = defaultMessage;

    }
    public int getHttpStatusCode(){

        return convertToHttpStatus(this);

    }
    public static StatusCode valueOf(int code) {

        for (StatusCode value : StatusCode.values()) {

            if (value.code == code) {

                return value;

            }

        }

        throw new IllegalArgumentException("没有符合'" + code + "'的StatusCode");

    }
    public static int convertToHttpStatus(StatusCode statusCode) {

        return statusCode.code / 1000;

    }
    public static int convertToHttpStatus(int code) {

        return convertToHttpStatus(valueOf(code));

    }

}


