package com.hz.management.dao;

import com.hz.management.entity.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferDao  extends JpaRepository<Offer,Integer> {

    Long count(Specification<Offer> specification);

    List<Offer> findAll(Specification<Offer> specification);

    Page<Offer> findAll(Specification<Offer> specification, Pageable pageable);

    Offer findOfferByStockAndSpecificationAndUnit(String stock,String specification, String unit);

    List<Offer> findAllByStockAndStatus(String stock,Boolean status);
}
