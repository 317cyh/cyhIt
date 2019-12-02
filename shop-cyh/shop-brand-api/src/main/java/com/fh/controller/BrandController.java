package com.fh.controller;

import com.fh.service.BrandService;
import com.fh.utils.ResponseServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("brandapi")
@CrossOrigin(maxAge = 3600, origins = "http://localhost:8888")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /*查询大类型相关的品牌*/
    @GetMapping("queryBrandInfo/{pid}")
    public ResponseServer queryBrandInfo(@PathVariable String pid){
        Object brandInfo=brandService.queryBrandInfo(pid);
        return ResponseServer.success(brandInfo);
    }
}
