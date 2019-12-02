package com.fh.controller;

import com.fh.model.UserBean;
import com.fh.service.LoginService;
import com.fh.utils.JwtUtils;
import com.fh.utils.ResponseServer;
import com.fh.utils.ServerEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/loginapi")
@CrossOrigin(maxAge = 3600, origins = "http://localhost:8888")
public class LoginController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private LoginService loginService;

    /*获取验证码*/
    @PostMapping("/getYzm")
    public ResponseServer getYzm(String phone){
        if(phone!=""&& phone!=null){
            try {
               /* String code = sms.sendSms(phone);*/
                /*redisTemplate.opsForValue().set("code_"+phone,"123456");*/
                redisTemplate.opsForValue().set("code_"+phone,"123456",300, TimeUnit.SECONDS);
            }catch (Exception e){
                return ResponseServer.error(ServerEnum.ERROR);
            }

        }
        return ResponseServer.success(ServerEnum.SUCCESS);
    }

    /*判断登录并且判断手机号存在*/
    @PostMapping("/userLogin")
    public ResponseServer userLogin(String phone,String messageCode){
          Map<String,Object> map=new HashMap<String,Object>();
          String token="";
        if(phone!="" || messageCode!=""){
            String code = (String) redisTemplate.opsForValue().get("code_" + phone);
            if(messageCode.equals(code)){
                redisTemplate.delete("code_" + phone);

              UserBean userBean= loginService.queryUserPhone(phone);
              if(userBean!=null){
                  redisTemplate.opsForValue().set("user_"+phone,userBean.getUserphone());
                  redisTemplate.opsForValue().set("cartid_"+phone,userBean.getCartid());
                  map.put("phone",userBean.getUserphone());
                   token = JwtUtils.createToken(map);

              }else {
                  UserBean userBean1=new UserBean();
                  userBean1.setUserphone(phone);
                  userBean1.setCartid(UUID.randomUUID().toString());
                  loginService.addUser(userBean1);
                  redisTemplate.opsForValue().set("user_"+phone,userBean1.getUserphone());
                  redisTemplate.opsForValue().set("cartid_"+phone,userBean1.getCartid());
                  map.put("phone",userBean.getUserphone());
                  token = JwtUtils.createToken(map);

              }

            }else {
                return ResponseServer.error(ServerEnum.YZM_ERREO);
            }
        }else {
            return ResponseServer.error(ServerEnum.PWD_ERROR);
        }
       return ResponseServer.success(token);

    }

}
