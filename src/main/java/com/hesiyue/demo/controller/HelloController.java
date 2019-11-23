package com.hesiyue.demo.controller;

import com.hesiyue.demo.GoodsDetailWithNum;
import com.hesiyue.demo.Result;
import com.hesiyue.demo.entity.*;
import com.hesiyue.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api")
@CrossOrigin
public class HelloController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GoodRepository goodRepository;

    @Autowired
    ImgRepository imgRepository;

    @Autowired
    GoodsDetailRepository goodsDetailRepository;

    @Autowired
    CartRepository cartRepository;

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

    @PostMapping("/queryGoodsImgs")
    public List<ImgEntity> queryGoodsImgs(@RequestParam("imgid") String imgid){
        return imgRepository.findByImgid(imgid);
    }

    @PostMapping("/queryGoodsDetail")
    public GoodsDetailEntity queryGoodsDetail(@RequestParam("id") String id){
        return goodsDetailRepository.findById(id);
    }

    @PostMapping("/queryByKind")
    public List<GoodEntity> findByKind(@RequestParam("kind") String kind){
        return goodRepository.findByKind(kind);
    }

    @PostMapping("/updateCartGoodsNum")
    public Result updateCartGoodsNum(@RequestParam("userID") int userID,@RequestParam("goodsID") String goodID,@RequestParam("num")int num){
        CartEntity cartEntity = cartRepository.findCartEntityByUseridAndGoodsid(userID,goodID);
        if(cartEntity != null){
            if(num==1){
                cartEntity.setCount(cartEntity.getCount()+1);
            }
            if(cartRepository.save(cartEntity) != null){
                return new Result(200);
            }
            else
                return new Result(400);
        }else {
            cartEntity = new CartEntity();
            cartEntity.setUserid(userID);
            cartEntity.setGoodsid(goodID);
            cartEntity.setCount(1);
            cartRepository.save(cartEntity);
            return new Result(200);
        }

    }
    @PostMapping("/getBalance")
    public double getBalance(@RequestParam("userID") int userID){
        return userRepository.findUserEntityByUserid(userID).getBalance();
    }

    @PostMapping("/toCalculate")
    public Result toCalculate(@RequestParam("userID") int userID,@RequestParam("goodsID") String goodID){
        CartEntity cartEntity = cartRepository.findCartEntityByUseridAndGoodsid(userID,goodID);
        GoodsDetailEntity goodsDetailEntity = goodsDetailRepository.findById(goodID);
        double price = cartEntity.getCount()*goodsDetailEntity.getPrice();
        UserEntity userEntity = userRepository.findUserEntityByUserid(userID);
        Result result = new Result();
        if(userEntity.getBalance()>=price){
            userEntity.setBalance(userEntity.getBalance()-price);
            userRepository.save(userEntity);
            cartRepository.deleteById(cartEntity.getCartid());
            result.userEntity = userEntity;
            result.setCode(200);
            return  result;
        }else
            result.setCode(400);
            return  result;

    }


    @PostMapping(value = "/getCart")
    public Result getCart(@RequestParam("userID") int userID){
        Result result = new Result();
        List<CartEntity>cartEntity = cartRepository.findCartEntitiesByUserid(userID);
        List<GoodsDetailWithNum> goodsDetailWithNums = new ArrayList<>();
        result.userID = userID;
        for(CartEntity cart:cartEntity){
            int count = cart.getCount();
            GoodsDetailWithNum g1 = new GoodsDetailWithNum();
            g1.setGoodsDetailEntity(queryGoodsDetail(cart.getGoodsid()));
            g1.setNum(count);
            goodsDetailWithNums.add(g1);
        }
        result.list.addAll(goodsDetailWithNums);
        return result;

    }

    //PostMapping与requestbody搭配，通过json传递     GetMapping与requestParam搭配，通过路径或者参数指定?username=  的方式
    //@PathVariable支持restful风格
    @PostMapping(value = "/login")
    public Result login(@RequestBody UserEntity userEntity){
        String username = userEntity.getUsername();
        String password = userEntity.getPassword();
        UserEntity userEntity1 = new UserEntity();
        Result result = new Result();
        if((userEntity1=userRepository.findUserEntityByUsernameAndPassword(username,password))!=null){
            List<CartEntity>cartEntity = cartRepository.findCartEntitiesByUserid(userEntity1.getUserid());
            List<GoodsDetailWithNum> goodsDetailWithNums = new ArrayList<>();
            result.userID = userEntity1.getUserid();
            for(CartEntity cart:cartEntity){
                int count = cart.getCount();
                GoodsDetailWithNum g1 = new GoodsDetailWithNum();
                g1.setGoodsDetailEntity(queryGoodsDetail(cart.getGoodsid()));
                g1.setNum(count);
                goodsDetailWithNums.add(g1);
            }
            result.list.addAll(goodsDetailWithNums);
            result.userEntity = userEntity1;
            result.setCode(200);
            return result;
        }else {
            System.out.println("没有查询到该用户的信息");
            result.setCode(401);
            return result;
        }

    }
}
