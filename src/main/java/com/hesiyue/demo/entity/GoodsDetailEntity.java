package com.hesiyue.demo.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Table(name = "good_detail_table")
@Component
public class GoodsDetailEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name="title")
    private String title;

    @Column(name = "oldprice")
    private double oldprice;

    @Column(name = "price")
    private double price;

    @Column(name = "desc")
    private String desc;
}
