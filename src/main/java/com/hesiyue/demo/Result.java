package com.hesiyue.demo;

import com.hesiyue.demo.entity.GoodsDetailEntity;
import com.hesiyue.demo.entity.UserEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {
    private int code;

    public Result(int code){
        this.code = code;
    }

    public Result(){}

    public int getCode(){
        return this.code;
    }

    public void setCode(int code){
        this.code = code;
    }

    public int userID;

    public List<GoodsDetailWithNum> list = new ArrayList<>();

    public UserEntity userEntity;
}
