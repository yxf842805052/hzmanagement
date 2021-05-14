package com.hz.management.dao;

import com.hz.management.entity.Storage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StorageDao extends JpaRepository<Storage,Integer> {
    Long count(Specification<Storage> specification);

    List<Storage> findAll(Specification<Storage> specification);

    Page<Storage> findAll(Specification<Storage> specification, Pageable pageable);

}
