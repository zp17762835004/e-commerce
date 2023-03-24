package com.all.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor

public class AccountBalanceDto {
    private BigDecimal accountBalance;
}
