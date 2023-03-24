package com.all.ecommerce.config;

import com.all.ecommerce.common.exception.AccountException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
    @ExceptionHandler(AccountException.class)
    public ResponseEntity handleBusinessException(AccountException accountException){
        return ResponseEntity.status(accountException.getStatusCode().getHttpStatusCode())
                .body(new Error(accountException));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleUnknownException(Exception e){
        log.info("服务器未知异常",e);
        return ResponseEntity.status(500).body(new Error(e));
    }
}
