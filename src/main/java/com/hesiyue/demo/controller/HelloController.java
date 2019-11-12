package com.hesiyue.demo.controller;

import com.hesiyue.demo.Result;
import com.hesiyue.demo.entity.GoodEntity;
import com.hesiyue.demo.entity.UserEntity;
import com.hesiyue.demo.repository.GoodRepository;
import com.hesiyue.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "api")
@CrossOrigin
public class HelloController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GoodRepository goodRepository;

    @GetMapping("/hello")
    public String getindex(){
        return "hello world";
    }

    @GetMapping("/queryall")
    public List<UserEntity> queryall(){
        return  userRepository.findAll();
    }

    @GetMapping("/queryGoodsAll")
    public List<GoodEntity> queryGoodsAll(){
        return goodRepository.findAll();
    }

    @PostMapping("/queryGoodsByWays")
    public List<GoodEntity> queryGoodsByWays(@RequestParam("type") String type){
        return goodRepository.findByType(type);

    }

g

    //PostMapping与requestbody搭配，通过json传递     GetMapping与requestParam搭配，通过路径或者参数指定?username=  的方式
    //@PathVariable支持restful风格
    @GetMapping("/check")
    public Integer logincheck(@RequestParam String username, @RequestParam String password){
        if(userRepository.findUserEntityByUsernameAndPassword(username,password)!=null){
            return 200;
        }else {
            return 400;
        }

    }

    @PostMapping(value = "/login")
    public Result login(@RequestBody UserEntity userEntity){
        String username = userEntity.getUsername();
        String password = userEntity.getPassword();
        if(userRepository.findUserEntityByUsernameAndPassword(username,password)!=null){
            return new Result(200);
        }else {
            return new Result(400);
        }

    }
}
