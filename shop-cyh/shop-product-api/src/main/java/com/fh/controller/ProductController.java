package com.fh.controller;

import com.fh.model.ShopProduct;
import com.fh.model.ShopProductPage;
import com.fh.service.ProductService;
import com.fh.utils.PageBean;
import com.fh.utils.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("productapi")
@CrossOrigin(maxAge = 3600, origins = "http://localhost:8888",exposedHeaders="NOLONGIN")
public class ProductController {
    @Autowired
    private ProductService productService;


    /*商品查询*/
    @PostMapping("queryProduct")
    public ResponseServer queryProduct(PageBean<ShopProduct> pageBean, ShopProductPage shopProductPage){
         pageBean= productService.queryProduct(pageBean,shopProductPage);
        return ResponseServer.success(pageBean);
    }
}
