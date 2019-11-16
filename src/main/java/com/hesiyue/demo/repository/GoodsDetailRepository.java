package com.hesiyue.demo.repository;


import com.hesiyue.demo.entity.GoodsDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsDetailRepository extends JpaRepository<GoodsDetailEntity,Integer> {

     public GoodsDetailEntity findById(String id);
}
