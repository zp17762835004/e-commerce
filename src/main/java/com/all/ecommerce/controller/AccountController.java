package com.all.ecommerce.controller;

import com.all.ecommerce.common.annotation.PermissionAnnotation;
import com.all.ecommerce.dto.AccountBalanceDto;
import com.all.ecommerce.entity.Bill;
import com.all.ecommerce.service.AccountService;
import com.all.ecommerce.common.utils.JsonUtil;
import com.all.ecommerce.common.utils.Result;
import com.all.ecommerce.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/account")
/**
    假设在注册账户过程中已对账户名称account_name做了重复验证
 */
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private BillService billService;
    /**
     * 1.  查询用户钱包余额
     * @param accountName
     * @return 对应账户名称的账户余额
     */
    @GetMapping("/getAccountBalance")
    public String getAccountBalance(String accountName){
        BigDecimal accountBalance = accountService.getAccountBalance(accountName);
        if(accountBalance!=null){
            return JsonUtil.getJson(Result.succeed(new AccountBalanceDto(accountBalance),"查询成功"));
        }else{
            return JsonUtil.getJson(Result.failed(null,"查询失败"));
        }
    }

    /**
     * 2.   用户消费100元的接口
     * @param accountName
     * @param monetary
     * @return
     */
    @PostMapping("/consume")


    public String consume(String accountName,BigDecimal monetary){

        //题目要求消费100元
        monetary = BigDecimal.valueOf(100);


        Boolean result = accountService.consume(accountName,monetary);
        System.out.println(result);
        if (result) {
            return JsonUtil.getJson(Result.succeed(null,"消费成功"));
        }else{
            return JsonUtil.getJson(Result.succeed(null,"消费失败"));
        }
    }

    /**
     * 3.   用户退款20元接口
     * @param accountName
     * @param refundMoney
     * @return
     */
    @PostMapping("/refund")
    public String refund(String accountName,BigDecimal refundMoney){
        //题目要求退款20元
        refundMoney = BigDecimal.valueOf(20);

        Boolean result = accountService.refund(accountName, refundMoney);
        if (result) {
            return JsonUtil.getJson(Result.succeed(null,"退款成功"));
        }else{
            return JsonUtil.getJson(Result.succeed(null,"退款失败"));
        }
    }






}
