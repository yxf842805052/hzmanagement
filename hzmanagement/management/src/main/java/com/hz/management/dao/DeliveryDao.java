package com.hz.management.dao;

import com.hz.management.entity.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryDao extends JpaRepository<Delivery,Integer> {
    Long count(Specification<Delivery> specification);

    List<Delivery> findAll(Specification<Delivery> specification);

    Page<Delivery> findAll(Specification<Delivery> specification, Pageable pageable);
}
