package com.hesiyue.demo.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Table(name = "goods_img_table")
@Component
public class ImgEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "url")
    private String url;

    @Column(name = "imgid")
    private String imgid;
}
