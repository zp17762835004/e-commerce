package com.all.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @TableId(value = "bill_id", type = IdType.AUTO)
    private Integer billId;
    private String accountName;
    private String transactionType;
    private BigDecimal transactionAmount;
    private Date transactionDate;
}
