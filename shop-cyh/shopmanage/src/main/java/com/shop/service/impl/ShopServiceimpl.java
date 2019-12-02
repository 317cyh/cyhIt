package com.shop.service.impl;

import com.shop.mapper.ShopMapper;
import com.shop.model.ShopBrand;
import com.shop.model.ShopProduct;
import com.shop.model.ShopType;
import com.shop.service.ShopService;
import com.shop.utils.OssTest;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

@Service

public class ShopServiceimpl implements ShopService {
    @Autowired
    private ShopMapper shopMapper;

    @Override
    public List<ShopType> queryType() {
        return shopMapper.queryType();
    }

    @Override
    public void addShopType(ShopType shopType) {
        shopMapper.addShopType(shopType);
    }

    @Override
    public void deleteShopType(String id) {
        shopMapper.deleteShopType(id);
        shopMapper.deleteShopTypeTwo(id);
    }

    @Override
    public void updateShopType(ShopType shopType) {
        shopMapper.updateShopType(shopType);
    }

    @Override
    public Integer queryCountz(ShopBrand shopBrand) {
        return shopMapper.queryCountz(shopBrand);
    }

    @Override
    public List<ShopBrand> queryResource(ShopBrand shopBrand) {
        return shopMapper.queryResource(shopBrand);
    }

    @Override
    public void delteShopBrand(String id) {
        shopMapper.delteShopBrand(id);
    }

    @Override
    public void addShopBrand(ShopBrand shopBrand) {

        try {
            OssTest os = new OssTest();
            File fs = new File(shopBrand.getImagepath());
            FileInputStream fileInputStream = new FileInputStream(fs);
            MultipartFile multipartFile = new MockMultipartFile(fs.getName(), fs.getName(),
                    ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
            String s = os.checkImage(multipartFile);
            shopBrand.setImagepath(s);
        } catch (Exception e) {


        }
        shopMapper.addShopBrand(shopBrand);
    }

    @Override
      public void addShopProduct(ShopProduct shopProduct) {
        try {
            OssTest os = new OssTest();
            FileInputStream fileInputStream=null;
            MultipartFile multipartFile=null;
            File fa = new File(shopProduct.getMain_img());
             fileInputStream = new FileInputStream(fa);
             multipartFile = new MockMultipartFile(fa.getName(), fa.getName(),
                    ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
            String s = os.checkImage(multipartFile);
            shopProduct.setMain_img(s);
        } catch (Exception e) {
        }
        shopProduct.setCreate_time(new Date());
        shopMapper.addShopProduct(shopProduct);


    }

    @Override
    public List<ShopBrand> queryBrandId() {
        return shopMapper.queryBrandId();
    }

    @Override
    public ShopBrand updateShopBrand(String id) {
        return shopMapper.updateShopBrand(id);
    }
}