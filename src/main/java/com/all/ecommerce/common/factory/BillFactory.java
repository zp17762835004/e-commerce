package com.all.ecommerce.common.factory;

import com.all.ecommerce.entity.Bill;
import com.all.ecommerce.enums.TransactionType;

import java.math.BigDecimal;
import java.util.Date;

public class BillFactory {
    public static Bill createBill(String accountName, TransactionType transactionType, BigDecimal transactionAmount){
        Date date = new Date();
        return new Bill(null,accountName,transactionType.getTransactionDescription(),transactionAmount,date);
    }
}
