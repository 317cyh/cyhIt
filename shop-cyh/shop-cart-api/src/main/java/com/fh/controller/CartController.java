package com.fh.controller;

import com.fh.aopexception.LoginAnnotation;
import com.fh.service.CartService;
import com.fh.utils.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("cartapi")
@CrossOrigin(maxAge = 3600, origins = "http://localhost:8888",exposedHeaders="NOLONGIN")
public class CartController {

    @Autowired
    private CartService cartService;

    //加入购物车
    @PostMapping("addCart")
    @LoginAnnotation
    public ResponseServer addCart(String ProductId, HttpServletRequest request){
        String phone = (String) request.getAttribute("phone");
        try {
            long productSize= cartService.addCart(ProductId,phone);
            return ResponseServer.success(productSize);
        }catch (Exception e){
            System.out.println(e);
            return ResponseServer.error();
        }


    }
    //跳转购物车

    @GetMapping("lookCart")
    @LoginAnnotation
    public ResponseServer lookCart(){
        return ResponseServer.success();
    }

    //查询资料

    @GetMapping("queryCartInfo")
    @LoginAnnotation
    public ResponseServer queryCartInfo(HttpServletRequest request){
        String phone = (String) request.getAttribute("phone");
        Map<String,Object>map= cartService.queryCartInfo(phone);
          return ResponseServer.success(map);
    }
    //删除商品
    @PostMapping("deleteCartInfo")
    @LoginAnnotation
    public ResponseServer deleteCartInfo(HttpServletRequest request,String id){
        try {
            String phone = (String) request.getAttribute("phone");
            cartService.deleteCartInfo(phone,id);
            return ResponseServer.success();
        }catch (Exception e){
            return ResponseServer.error();
        }
    }

    /*减一个*/
    @GetMapping("removeNUmProduct/{id}")
    @LoginAnnotation
    public ResponseServer removeNUmProduct(HttpServletRequest request,@PathVariable String id){
        try {
            String phone = (String) request.getAttribute("phone");
            cartService.removeNUmProduct(phone,id);
            return ResponseServer.success();
        }catch (Exception e){
            return ResponseServer.error();
        }
    }

    /*加一个*/
    @GetMapping("addProduct/{id}")
    @LoginAnnotation
    public ResponseServer addProduct(HttpServletRequest request,@PathVariable String id){
        try {
            String phone = (String) request.getAttribute("phone");
            cartService.addProduct(phone,id);
            return ResponseServer.success();
        }catch (Exception e){
            return ResponseServer.error();
        }
    }

    /*全选*/
    @GetMapping("batchAll")
    @LoginAnnotation
    public ResponseServer batchAll(HttpServletRequest request){
        try {
            String phone = (String) request.getAttribute("phone");
            cartService.batchAll(phone);
            return ResponseServer.success();
        }catch (Exception e){
            return ResponseServer.error();
        }
    }

    /*单选*/
    @GetMapping("radioCart/{id}")
    @LoginAnnotation
    public ResponseServer radioCart(HttpServletRequest request,@PathVariable String id){
        try {
            String phone = (String) request.getAttribute("phone");
            cartService.radioCart(phone,id);
            return ResponseServer.success();
        }catch (Exception e){
            return ResponseServer.error();
        }
    }
}
