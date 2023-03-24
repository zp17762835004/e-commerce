package com.all.ecommerce.service;

import com.all.ecommerce.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

public interface AccountService extends IService<Account> {
   BigDecimal getAccountBalance(String accountName);

   Boolean consume(String aacountName,BigDecimal monetary);

   Boolean refund(String accountName,BigDecimal refundMoney);
}
