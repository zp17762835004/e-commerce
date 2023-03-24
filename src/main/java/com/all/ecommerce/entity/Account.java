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
public class Account {
    @TableId(value = "account_id", type = IdType.AUTO)
    private Integer accountId;
    private String accountName;
    private Date updateDate;
    private BigDecimal accountBalance;
}
