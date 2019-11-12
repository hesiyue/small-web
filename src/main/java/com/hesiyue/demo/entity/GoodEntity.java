package com.hesiyue.demo.entity;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Table(name = "goods_table")
@Component
public class GoodEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "img")
    private String img;

    @Column(name = "message")
    private String message;

    @Column(name = "price")
    private double price;

    @Column(name = "collect")
    private int collect;

    @Column(name = "type")
    private String type;
}
