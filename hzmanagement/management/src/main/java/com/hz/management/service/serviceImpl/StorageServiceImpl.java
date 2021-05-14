package com.hz.management.service.serviceImpl;

import com.hz.management.dao.OfferDao;
import com.hz.management.dao.StorageDao;
import com.hz.management.entity.Offer;
import com.hz.management.entity.Storage;
import com.hz.management.service.StorageService;
import com.hz.management.tool.PageUtil;
import com.hz.management.view.DataView;
import com.hz.management.view.DetailsView;
import com.hz.management.view.StorageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {

    private static String TIMETYPE = "yyyy-MM-dd";

    private SimpleDateFormat sdf = new SimpleDateFormat(TIMETYPE);


    @Autowired
    private StorageDao storageDao;

    @Autowired
    private OfferDao offerDao;


    @Override
    @Transactional
    public DataView addStorageList(List<StorageView> storageViewList) {
        for(StorageView s: storageViewList){
            List<DetailsView> detailsList = s.getDetails();
            String format = sdf.format(new Date());
            Date parse = null;
            try {
                 parse = sdf.parse(format);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            for(DetailsView d:detailsList){
                System.out.println(s);
                Storage save = storageDao.save(new Storage(s.getStock(), d.getSpecification(), d.getUnit(), d.getAmount(),parse));
                if(null == save){
                    return new DataView(601,"进货表添加失败");
                }else{
                    //查新是否存在报价
                    Offer offerOne = offerDao.findOfferByStockAndSpecificationAndUnit(s.getStock(), d.getSpecification(), d.getUnit());
                    //添加成功 将数量添加至相对应的offer表中
                    if(null != offerOne){
                        //如果存在报价表信息
                            //数量叠加,并设置到offerOne中
                        offerOne.setQuantity(d.getAmount().intValue() + offerOne.getQuantity().intValue());
                        Offer offerEnd = offerDao.save(offerOne);
                        if(null == offerEnd){
                            return new DataView(601,"报价表更新库存添加失败");
                        }

                    }
                    else{
                        //不存在报价表信息，那么添加报价表信息
                        Offer  offer = offerDao.save(new Offer(save.getStock(),save.getSpecification(),save.getUnit(),null,save.getQuantity(),d.isStatus()));
                        if( null == offer ){
                            return new DataView(601,"报价表新增报价信息添加失败");
                        }
                    }
                }
            }
        }
        return new DataView("所有数据添加成功",200);
    }

    @Override
    public DataView storageList(int pageNo, int pageSize, String query, String time) {
        List<Storage> all = null;
        // 构造自定义查询条件
        Specification<Storage> queryCondition = new Specification<Storage>() {
            @Override
            public Predicate toPredicate(Root<Storage> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (null != query && !query.equals("")) {
                    predicateList.add(criteriaBuilder.equal(root.get("stock"), query));
                }
                if(null != time && !time.equals("")){
                    try {
                        Date parse = sdf.parse(time);
                        predicateList.add(criteriaBuilder.equal(root.get("date"), parse));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        //总条数
        Long count = storageDao.count(queryCondition);

        // 分页和不分页，这里按起始页和每页展示条数为0时默认为不分页，分页的话按创建时间降序
        try {
            if (pageNo == 0 && pageSize == 0) {
                all = storageDao.findAll(queryCondition);
            } else {
                all = storageDao.findAll(queryCondition, PageRequest.of(pageNo - 1, pageSize)).getContent();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return all != null ? new DataView(new PageUtil<Storage>(pageNo,pageSize,count.intValue(),all), 200) : new DataView("没有数据", 601);
    }
}
