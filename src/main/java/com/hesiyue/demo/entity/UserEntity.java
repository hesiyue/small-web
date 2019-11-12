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
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
}
