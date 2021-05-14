package com.hz.management.service.serviceImpl;

import com.hz.management.dao.DeliveryDao;
import com.hz.management.dao.OfferDao;
import com.hz.management.entity.Offer;
import com.hz.management.service.OfferService;
import com.hz.management.tool.PageUtil;
import com.hz.management.view.DataView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferDao offerDao;

    @Autowired
    private DeliveryDao deliveryDao;

    @Override
    public boolean addPriceList(Offer offer) {
        Offer save = offerDao.save(offer);
        //返回不等于空说明添加成功
        return save != null;
    }

    @Override
    public DataView priceList(int pageNo, int pageSize, String query) {
        List<Offer> all = null;
        // 构造自定义查询条件
        Specification<Offer> queryCondition = new Specification<Offer>() {
            @Override
            public Predicate toPredicate(Root<Offer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (null != query && !query.equals("")) {
                    predicateList.add(criteriaBuilder.equal(root.get("stock"), query));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        //总条数
        Long count = offerDao.count(queryCondition);

        // 分页和不分页，这里按起始页和每页展示条数为0时默认为不分页，分页的话按创建时间降序
        try {
            if (pageNo == 0 && pageSize == 0) {
                all = offerDao.findAll(queryCondition);
            } else {
                all = offerDao.findAll(queryCondition, PageRequest.of(pageNo - 1, pageSize)).getContent();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return all != null ? new DataView(new PageUtil<Offer>(pageNo,pageSize,count.intValue(),all), 200) : new DataView("没有数据", 601);
    }

    @Override
    public DataView removePriceList(int id) {
        try{
            offerDao.deleteById(id);
            return new DataView("删除成功",200);
        }catch(Exception e){
            e.printStackTrace();
            return new DataView("删除失败",601);
        }
    }

    @Override
    public DataView updatePriceList(Offer offer) {
        Optional<Offer> byId = offerDao.findById(offer.getId());
        Offer target = byId.get();
        if(null!= target){
            target.setUnitPrice(offer.getUnitPrice());
            Offer save = offerDao.save(target);
            return null != save ? new DataView("报价表修改成功",200) : new DataView(601,"报价表修改失败");
        }
        return new DataView(601,"报价表修改失败，产品入库时可能出现问题");
    }
}
