package com.hesiyue.demo.repository;

import com.hesiyue.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    public UserEntity findUserEntityByUsernameAndPassword(String username,String password);
}
