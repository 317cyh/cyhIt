package com.fh.service.impl;

import com.fh.dao.BrandDao;
import com.fh.model.ShopBrand;
import com.fh.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDao brandDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Object queryBrandInfo(String pid) {
        Boolean shopBrand = redisTemplate.hasKey("shopBrand");
        List<ShopBrand> brandList=new ArrayList<>();
        if(shopBrand){
            brandList= (List<ShopBrand>) redisTemplate.opsForValue().get("shopBrand");
        }else {
            brandList= brandDao.queryBrandInfo();
            redisTemplate.opsForValue().set("shopBrand",brandList);
        }
        List<ShopBrand> returnList= brandList.stream()
                .filter(brand->brand.getTypeName().contains(pid))
                .collect(Collectors.toList());

        return returnList;
    }
}
