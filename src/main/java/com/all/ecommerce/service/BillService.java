package com.all.ecommerce.service;

import com.all.ecommerce.entity.Bill;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BillService extends IService<Bill> {
    List<Bill> getBills(String accountName);
}
