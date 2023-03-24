package com.all.ecommerce.controller;

import com.all.ecommerce.common.utils.JsonUtil;
import com.all.ecommerce.common.utils.Result;
import com.all.ecommerce.entity.Bill;
import com.all.ecommerce.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;

    /**
     * 4.   查询用户钱包金额变动明细的接口
     * @param accountName
     * @return
     */
    @GetMapping("getBill")
    public String getBill(String accountName){
        List<Bill> bills = billService.getBills(accountName);
        return JsonUtil.getJson(Result.succeed(bills,"查询成功"));
    }
}
