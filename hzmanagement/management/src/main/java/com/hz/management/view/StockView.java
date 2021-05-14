package com.hz.management.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockView {
    //商品
    private String stock;
    //商品规格
    private String specification;
    //单位
    private String unit;
    //数量
    private Integer quantity;
    //单价
    private Float unitPrice;
    //价格
    private Integer money;

    @Override
    public String toString() {
        return "StockView{" +
                "stock='" + stock + '\'' +
                ", specification='" + specification + '\'' +
                ", unit='" + unit + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", money=" + money +
                '}';
    }
}
