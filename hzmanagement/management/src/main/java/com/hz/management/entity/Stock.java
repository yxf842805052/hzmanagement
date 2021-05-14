package com.hz.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * 送货单中的每一条数据
 */
@Entity
@Table( name = "Stock")
@Getter
@Setter
@ToString
public class Stock {
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
    //数量
    @Column(name = "quantity")
    private Integer quantity;
    //单价
    @Column(name = "unitPrice")
    private Float unitPrice;
    //价格
    @Column(name = "money")
    private Integer money;

    @ManyToOne(cascade = CascadeType.ALL)
    private Delivery delivery;
}
