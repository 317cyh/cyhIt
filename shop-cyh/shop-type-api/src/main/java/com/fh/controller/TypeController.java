package com.fh.controller;

import com.fh.service.TypeService;
import com.fh.utils.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/typeapi")
@CrossOrigin(maxAge = 3600, origins = "http://localhost:8888",exposedHeaders="NOLONGIN")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("/queryTypeInfo")
 /*   @LoginAnnotation*/
    public ResponseServer queryTypeInfo(){
        boolean shopType = redisTemplate.hasKey("shopType");
        Object shopInfo=null;
        if(shopType){
            shopInfo=  redisTemplate.opsForValue().get("shopType");
        }else {
            shopInfo=typeService.queryTypeInfo();
            redisTemplate.opsForValue().set("shopType",shopInfo);
        }
        return ResponseServer.success(shopInfo);
    }

}
