package com.hz.management.controller;

import com.hz.management.entity.Offer;
import com.hz.management.service.OfferService;
import com.hz.management.view.DataView;
import com.hz.management.view.OfferView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OfferController {

    @Autowired
    private OfferService offerService;

    @PostMapping("/add/price/list")
    public DataView addPriceList(
        @RequestBody OfferView offerView
    ){
        Offer offer = new Offer();
        BeanUtils.copyProperties(offerView,offer);
        if( offerService.addPriceList(offer) ){
            return new DataView("添加成功",200);
        }
        return new DataView("添加失败",601);
    }

    @PutMapping("/update/price/list")
    public DataView updatePriceList(
            @RequestBody OfferView offerView
    ){
        Offer offer = new Offer();
        BeanUtils.copyProperties(offerView,offer);
        return offerService.updatePriceList(offer);
    }

    /**
     *
     * @param pageNo 当前页
     * @param pageSize 当前页数据量
     * @param query 搜索信息
     * @return
     */
    @GetMapping("/price/list")
    public DataView priceList(
            @RequestParam(name = "currentPage",defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "1") int pageSize,
            @RequestParam(defaultValue = "") String query
    ){
        System.out.println(pageNo+" "+pageSize+" "+query);
        return offerService.priceList(pageNo,pageSize,query);
    }

    @DeleteMapping("/remove/price/list")
    public DataView removePriceList(
            @RequestParam int id
    ){
        return offerService.removePriceList(id);
    }
}
