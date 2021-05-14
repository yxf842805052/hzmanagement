package com.hz.management.service.serviceImpl;

import com.hz.management.dao.DeliveryDao;
import com.hz.management.dao.OfferDao;
import com.hz.management.entity.Delivery;
import com.hz.management.entity.Offer;
import com.hz.management.service.DeliveryService;
import com.hz.management.tool.PageUtil;
import com.hz.management.view.DataView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.Predicate;
@Service
public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    private DeliveryDao deliveryDao;

    @Autowired
    private OfferDao offerDao;

    private static String TIMETYPE = "yyyy-MM-dd";

    private SimpleDateFormat sdf = new SimpleDateFormat(TIMETYPE);


    @Override
    public DataView addDelivery(Delivery delivery) {
        Delivery save = deliveryDao.save(delivery);
        return null != save ? new DataView(delivery.getType() == 1 ? "收据单添加成功" : "租赁订单添加成功",200) : new DataView(601,"租赁订单添加失败");
    }

    @Override
    public DataView getSpecificationList(String stock,Boolean status) {
        List<Offer> allByStockAndStatus = offerDao.findAllByStockAndStatus(stock, status);
        if( null != allByStockAndStatus){
            return new DataView(allByStockAndStatus,200);
        }
        return new DataView("没有查询到相应的规格信息",601);
    }

    @Override
    public DataView searchList(int pageNo, int pageSize, String startDate, String endDate, String query, String phone, String deposit, String type, String sort) {

        List<Delivery> all = new ArrayList<>();

        Specification<Delivery> queryCondition = new Specification<Delivery>(){
            @Override
            public Predicate toPredicate(Root<Delivery> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                //时间
                if(null != startDate && !startDate.equals("") && null != endDate && !endDate.equals("") ){
                    try {
                        Date sd = sdf.parse(startDate);
                        Date ed = sdf.parse(endDate);
                        predicateList.add(criteriaBuilder.between(root.get("date"),sd,ed));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //查询收货人电话的关键字
                if (null != phone && !phone.equals("")) {
                    predicateList.add(criteriaBuilder.equal(root.get("consigneePhone"), phone));
                }
                //查询预收租金
                if (null != deposit && !deposit.equals("")) {
                    predicateList.add(criteriaBuilder.equal(root.get("deposit"), new Integer(deposit)));
                }
                //订单类型
                if (null != type && !type.equals("")) {
                    predicateList.add(criteriaBuilder.equal(root.get("type"), new Integer(type)));
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };

        Long count = deliveryDao.count(queryCondition);

        // 分页和不分页，这里按起始页和每页展示条数为0时默认为不分页，分页的话按创建时间降序
        try {
            if (pageNo == 0 && pageSize == 0) {
                all = deliveryDao.findAll(queryCondition);
            } else {
                all = deliveryDao.findAll(queryCondition, PageRequest.of(pageNo - 1, pageSize,Sort.by("desc".equals(sort) ? Sort.Direction.DESC : Sort.Direction.ASC,"date"))).getContent();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return all != null ? new DataView(new PageUtil<Delivery>(pageNo,pageSize,count.intValue(),all), 200) : new DataView("没有数据", 601);
    }
}
