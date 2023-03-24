package com.all.ecommerce.service.impl;

import com.all.ecommerce.common.exception.AccountException;
import com.all.ecommerce.entity.Bill;
import com.all.ecommerce.mapper.BillMapper;
import com.all.ecommerce.service.BillService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.all.ecommerce.enums.StatusCode.BAD_AUTHENTICATION;

@Service
public class BillServiceImp extends ServiceImpl<BillMapper, Bill> implements BillService {
    @Autowired
    private BillMapper billMapper;
    @Override
    public List<Bill> getBills(String accountName) {
        List<Bill> bill = billMapper.selectList(Wrappers.<Bill>lambdaQuery()
                .eq(Bill::getAccountName, accountName));
        if(bill==null||bill.size()==0){
            throw new AccountException(BAD_AUTHENTICATION);
        }
        return bill;
    }
}
