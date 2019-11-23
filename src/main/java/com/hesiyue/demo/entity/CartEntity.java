package com.hesiyue.demo.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cart_table")
@Component
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartID")
    private int cartid;

    @Column(name = "userID")
    private int userid;

    @Column(name = "goodsID")
    private String goodsid;

    @Column(name = "count")
    private int count;
}
