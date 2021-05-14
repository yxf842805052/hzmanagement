package com.hz.management.controller;

import com.alibaba.fastjson.JSON;
import com.hz.management.entity.Delivery;
import com.hz.management.entity.Stock;
import com.hz.management.service.DeliveryService;
import com.hz.management.tool.BeanCopyUtil;
import com.hz.management.view.DataView;
import com.hz.management.view.DeliveryView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/search/list")
    public DataView searchList(@RequestParam(name = "currentPage" ,defaultValue = "1") int pageNo,
                               @RequestParam int pageSize,
                               @RequestParam String startDate,
                               @RequestParam String endDate,
                               @RequestParam String query,
                               @RequestParam String phone,
                               @RequestParam String deposit,
                               @RequestParam String type,
                               @RequestParam String sort
                               ) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date parse = sdf.parse(startDate);
//            System.out.println(parse);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//return null;
        return deliveryService.searchList(pageNo,pageSize,startDate,endDate,query,phone,deposit,type,sort);
    }



    //添加账单信息
    @PostMapping("/add/delivery")
    public DataView addDelivery(
            @RequestBody String value//前端添加账单数据
    ) {
        DeliveryView deliveryView = JSON.parseObject(value, DeliveryView.class);
        Delivery delivery = new Delivery();
        BeanUtils.copyProperties(deliveryView, delivery);
        List<Stock> stocksList = BeanCopyUtil.copyListProperties(deliveryView.getStocks(), Stock::new);
        for (Stock stock:stocksList){
            stock.setDelivery(delivery);
        }
        delivery.setStocks(stocksList);
        return deliveryService.addDelivery(delivery);
    }

    //查询出规格和单位
    @GetMapping("/delivery/specification/list")
    public DataView getSpecificationList(
            @RequestParam String stock,//前端添加账单数据
            @RequestParam Boolean status//查询出售产品0，查询出租产品1
    ){
        return deliveryService.getSpecificationList(stock,status);
    }
}

