package com.fh.controller;

import com.fh.model.ShopProduct;
import com.fh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/httpproductapi")
public class ProductHttpController {

    @Autowired
    private ProductService productService;


    @GetMapping("/queryProductInfo/{productId}")
    private ShopProduct queryProductInfo(@PathVariable String productId){

       ShopProduct sp= productService.queryProductInfo(productId);
        return sp;
    }
}
