package com.hz.management.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * 产品入库
 */
@Entity
@Table( name = "Storage")
@Getter
@Setter
@ToString
public class Storage {
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
    //时间
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    public Storage() {
    }

    public Storage(String stock, String specification, String unit, Integer quantity,Date date) {
        this.stock = stock;
        this.specification = specification;
        this.unit = unit;
        this.quantity = quantity;
        this.date = date;
    }
}
