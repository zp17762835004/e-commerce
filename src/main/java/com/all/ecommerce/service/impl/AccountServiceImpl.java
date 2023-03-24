package com.all.ecommerce.service.impl;

import com.all.ecommerce.common.exception.AccountException;
import com.all.ecommerce.common.factory.BillFactory;
import com.all.ecommerce.entity.Account;
import com.all.ecommerce.entity.Bill;
import com.all.ecommerce.mapper.AccountMapper;
import com.all.ecommerce.mapper.BillMapper;
import com.all.ecommerce.service.AccountService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.Date;

import static com.all.ecommerce.enums.StatusCode.BAD_AUTHENTICATION;
import static com.all.ecommerce.enums.StatusCode.NOT_SUFFICIENT_FUNDS;
import static com.all.ecommerce.enums.TransactionType.PAY;
import static com.all.ecommerce.enums.TransactionType.REFUND;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
@Autowired
private AccountMapper accountMapper;

@Autowired
private BillMapper billMapper;
    @Override
    public BigDecimal getAccountBalance(String accountName) {
        Account account = accountMapper.selectOne(Wrappers.<Account>lambdaQuery()
                .eq(Account::getAccountName,accountName));
        if(account!=null){
            return account.getAccountBalance();
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean consume(String aacountName, BigDecimal monetary) {
        //默认支付环节在操作数据库部分更易出现异常
        Account account = accountMapper.selectOne(Wrappers.<Account>lambdaQuery()
                .eq(Account::getAccountName, aacountName));
        if(account==null){
            throw new AccountException(BAD_AUTHENTICATION);
        }
        BigDecimal accountBalance = account.getAccountBalance();
        BigDecimal result = accountBalance.subtract(monetary);
        if(result.compareTo(BigDecimal.valueOf(0))<0){
            throw new AccountException(NOT_SUFFICIENT_FUNDS);
        }
        account.setAccountBalance(result);
        account.setUpdateDate(new Date());
        int i = accountMapper.updateById(account);

        //记录账单
        billMapper.insert(BillFactory.createBill(aacountName,PAY,monetary));
        //调用相应的支付api



        return i>0?true:false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean refund(String accountName, BigDecimal refundMoney) {
        //默认退款环节在操作数据库部分更易出现异常
        Account account = accountMapper.selectOne(Wrappers.<Account>lambdaQuery()
                .eq(Account::getAccountName, accountName));
        if(account==null){
            throw new AccountException(BAD_AUTHENTICATION);
        }
        BigDecimal accountBalance = account.getAccountBalance();
        account.setAccountBalance(accountBalance.add(refundMoney));
        account.setUpdateDate(new Date());
        int i = accountMapper.updateById(account);
        //记录账单
        billMapper.insert(BillFactory.createBill(accountName,REFUND,refundMoney));
        //调用相应的退款api


        return i>0?true:false;
    }
}
