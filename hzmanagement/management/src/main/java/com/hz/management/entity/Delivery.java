package com.hz.management.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 送货单总数据
 */
@Entity
@Table(name = "Delivery")
@Getter
@Setter
@ToString
public class Delivery {
    //主键
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    //联系地址
    @Column(name = "address")
    private String address;
    //时间
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") 入参格式
    @JsonFormat(pattern="yyyy-MM-dd")//出参格式
    private Date date;
    //总价
    @Column(name = "total")
    private Float total;
    //收货人
    @Column(name = "consignee")
    private String consignee;
    //收货人电话
    @Column(name = "consigneePhone")
    private String consigneePhone;
    //发货人
    @Column(name = "shipper")
    private String shipper;
    //发货人电话
    @Column(name = "shipperPhone")
    private String shipperPhone;
    //预付租金
    @Column(name = "deposit")
    private Integer deposit;
    //备注信息
    @Column(name = "remark")
    private String remark;
    //出货单类型,1收据单 0出租单
    @Column(name = "type")
    private Integer type;

    @OneToMany(
            mappedBy = "delivery",//定义表之间的关系
            cascade = CascadeType.ALL)//表之间的联级关系
//            fetch = FetchType.LAZY)//加载关系
    @JsonIgnoreProperties(value = {"delivery"})
    private List<Stock> stocks = new ArrayList<>();

}

