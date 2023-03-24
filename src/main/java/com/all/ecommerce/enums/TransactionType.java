package com.all.ecommerce.enums;

public enum TransactionType {
    PAY("支付"),
    REFUND("退款");
    private String transactionDescription;
    TransactionType(String transactionDescription){
        this.transactionDescription = transactionDescription;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }
}
