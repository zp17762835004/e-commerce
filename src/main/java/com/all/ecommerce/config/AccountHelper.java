package com.all.ecommerce.config;
import com.all.ecommerce.common.annotation.PermissionAnnotation;
import com.all.ecommerce.common.exception.AccountException;
import com.all.ecommerce.common.utils.TransactionUtils;
import com.all.ecommerce.entity.Account;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import static com.all.ecommerce.enums.StatusCode.BAD_AUTHENTICATION;

@Aspect
@Component
@Order(1)
public class AccountHelper {


    public AccountHelper(){

    }
//    @Pointcut("execution(* com.all.ecommerce.service.impl.AccountServiceImpl.consume())")
    @Pointcut("@annotation(com.all.ecommerce.common.annotation.PermissionAnnotation)")
    public void accountpoint(){}

//    @Before("accountpoint()")
//    public void beforeConsume(){
//
//        System.out.println("执行判断是否存在该账户");
//    }
    @Around(value = "@annotation(com.all.ecommerce.common.annotation.PermissionAnnotation)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {

        //在目标方法之前开启事务
        try {
            System.out.println("执行判断是否存在该账户");
            Object result = proceedingJoinPoint.proceed();

            System.out.println("之后需要执行的方法");
            return result;
        }catch (Throwable throwable){
            return null;
        }

    }
//    @AfterReturning("accountpoint()")
//    public void afterConsume(){
//        System.out.println("之后需要执行的方法");
//    }

}
