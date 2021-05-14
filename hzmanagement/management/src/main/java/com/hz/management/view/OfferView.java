package com.hz.management.view;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OfferView {
    private Integer id;
    //商品
    private String stock;
    //商品规格
    private String specification;
    //单位
    private String unit;
    //单价
    private Float unitPrice;
}
