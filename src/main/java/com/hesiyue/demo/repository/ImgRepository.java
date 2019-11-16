package com.hesiyue.demo.repository;

import com.hesiyue.demo.entity.ImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImgRepository extends JpaRepository<ImgEntity,Integer> {
    public List<ImgEntity> findByImgid(String imgid);
}
