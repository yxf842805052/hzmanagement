package com.hz.management.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * 产品报价表
 */
@Entity
@Table(name = "Offer")
@Getter
@Setter
@ToString
public class Offer {
    //主键
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    //商品
    @Column(name = "stock")
    private String stock;
    //商品规格
    @Column(name = "specification")
    private String specification;
    //单位
    @Column(name = "unit")
    private String unit;
    //单价
    @Column(name = "unitPrice")
    private Float unitPrice;
    //库存数量
    @Column(name = "quantity")
    private Integer quantity;
    //是否可租
    @Column(name = "status")
    private Boolean status;



    public Offer() {
    }

    public Offer(String stock, String specification, String unit, Float unitPrice, Integer quantity,Boolean status) {
        this.stock = stock;
        this.specification = specification;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.status = status;
    }
}
