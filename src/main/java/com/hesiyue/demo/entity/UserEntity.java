package com.hesiyue.demo.entity;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_table")
@Component
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "userID")
    private int userid;

    @Column(name = "balance")
    private double balance;


    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

}
