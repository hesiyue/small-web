package com.hesiyue.demo.repository;

import com.hesiyue.demo.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity,Integer> {

    public List<CartEntity> findCartEntitiesByUserid(int userid);

    public CartEntity findCartEntityByUseridAndGoodsid(int userid,String goodid);


}
