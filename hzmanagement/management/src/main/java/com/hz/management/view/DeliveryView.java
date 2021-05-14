package com.hz.management.view;

import com.hz.management.entity.Stock;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 页面传到后端的表单数据
 */
@Getter
@Setter
public class DeliveryView {
    private Integer id;
    //联系地址
    private String address;
    //时间
    private Date date;
    //总价
    private Float total;
    //收货人
    private String consignee;
    //收货人电话
    private String consigneePhone;
    //发货人
    private String shipper;
    //发货人电话
    private String shipperPhone;
    //备注信息
    private String remark;
    //预付租金
    private Integer deposit;
    //出单类型
    private Integer type;
    //            fetch = FetchType.LAZY)//加载关系
    private List<StockView> stocks = new ArrayList<>();

    @Override
    public String toString() {
        return "DeliveryView{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", date='" + date + '\'' +
                ", total=" + total +
                ", consignee='" + consignee + '\'' +
                ", consigneePhone='" + consigneePhone + '\'' +
                ", shipper='" + shipper + '\'' +
                ", shipperPhone='" + shipperPhone + '\'' +
                ", remark='" + remark + '\'' +
                ", stocks=" + stocks +
                '}';
    }
}
