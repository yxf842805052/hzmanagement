package com.hz.management.service;

import com.hz.management.entity.Offer;
import com.hz.management.view.DataView;
import org.springframework.data.jpa.domain.Specification;

public interface OfferService {

    //添加offer表数据
    boolean addPriceList(Offer offer);

    //获取offer表数据
    DataView priceList(int pageNo, int pageSize, String query);

    DataView removePriceList(int id);

    DataView updatePriceList(Offer offer);
}
