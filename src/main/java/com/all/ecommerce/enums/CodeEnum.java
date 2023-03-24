package com.all.ecommerce.enums;

import lombok.Getter;

@Getter
public enum CodeEnum {
    
    SUCCESS(200,"请求成功"),
    ERROR(500,"未知异常"),
    ERROR_EMPTY_RESULT(1001,"查询结果为空"),
    ERROR_INCOMPLETE_RESULT(1002,"请求参数不全");
    
    private int code = 200;
    private String message;
    CodeEnum(int code, String message){
        this.code = code;
        this.message = message;
    }
}