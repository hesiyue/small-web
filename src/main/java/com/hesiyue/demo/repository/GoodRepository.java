package com.hesiyue.demo.repository;

import com.hesiyue.demo.entity.GoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GoodRepository extends JpaRepository<GoodEntity,Integer> {
    public List<GoodEntity> findByType(String type);
    public List<GoodEntity> findByKind(String kind);

}
