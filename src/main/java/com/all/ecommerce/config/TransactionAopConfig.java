package com.all.ecommerce.config;

import com.all.ecommerce.common.utils.TransactionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

@Aspect
@Component
@Order(2)
public class TransactionAopConfig {
    @Autowired
    private TransactionUtils transactionUtils;

    @Around(value = "@annotation(com.all.ecommerce.common.annotation.TransActionAnnotation)")
    public Object transactionAround(ProceedingJoinPoint proceedingJoinPoint){
        TransactionStatus begin = transactionUtils.begin();
        try {
            Object result = proceedingJoinPoint.proceed();
            transactionUtils.commit(begin);
            return result;
        } catch (Throwable e) {
            System.out.println("进行回滚");
            transactionUtils.rollback(begin);
            return null;
        }
    }
}
