package com.shop.controller;

import com.shop.model.ShopBrand;
import com.shop.model.ShopProduct;
import com.shop.model.ShopType;
import com.shop.service.ShopService;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("test")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @Autowired
    private RedisTemplate redisTemplate;

    //去类型管理
    @RequestMapping("goType")
    public String goType() {
        return "ShopType";
    }

    //查找类型
    @RequestMapping("queryType")
    @ResponseBody
    public List queryType() {
        List<ShopType> list = shopService.queryType();
        return list;
    }

    //类型添加
    @RequestMapping("addShopType")
    @ResponseBody
    public String addShopType(ShopType shopType) {

        try {
            shopService.addShopType(shopType);
            redisTemplate.delete("shopType");
            return "1";
        } catch (Exception e) {
            return "2";
        }
    }

    //类型删除
    @RequestMapping("deleteShopType")
    @ResponseBody
    public String deleteShopType(String id) {
        try {
            shopService.deleteShopType(id);
            redisTemplate.delete("shopType");
            return "1";
        } catch (Exception e) {
            return "2";
        }
    }

    //类型修改
    @RequestMapping("updateShopType")
    @ResponseBody
    public String updateShopType(ShopType shopType) {
        try {
            shopService.updateShopType(shopType);
            redisTemplate.delete("shopType");
            return "1";
        } catch (Exception e) {
            return "2";
        }
    }

    //*******************************************************************
    //goShopBrand
    @RequestMapping("goShopBrand")
    public String goShopBrand() {
        return "ShopBrand";
    }

    //品牌查询
    @RequestMapping("queryShopBrand")
    @ResponseBody
    public Map queryData(ShopBrand shopBrand) {
        Map map = new HashMap();
        Integer count = shopService.queryCountz(shopBrand);
        List<ShopBrand> list = shopService.queryResource(shopBrand);
        map.put("data", list);
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("draw", shopBrand.getDrew());
        return map;
    }

    //品牌删除
    @RequestMapping("delteShopBrand")
    @ResponseBody
    public String delteShopBrand(String id) {
        try {
            shopService.delteShopBrand(id);
            redisTemplate.delete("shopBrand");
            return "1";
        } catch (Exception e) {
            return "2";
        }
    }

    @RequestMapping("uploadFile")
    @ResponseBody
    public String uploadFile(HttpServletRequest req) throws JSONException {
        MultipartHttpServletRequest request = (MultipartHttpServletRequest) req;
        MultipartFile multipartFile = request.getFile("file");
        String aaa = "";
        if (!multipartFile.isEmpty()) {
            String fileMultipartFileName = multipartFile.getOriginalFilename();
            String substring = fileMultipartFileName.substring(fileMultipartFileName.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString() + substring;
            File file = new File(req.getRealPath("/static/"));
            if (!file.exists()) {
                file.mkdirs();
                file.setWritable(true);
            }
            File tFile = new File(req.getRealPath("/static/"), fileName);

            aaa = tFile.toString();
            try {
                multipartFile.transferTo(tFile)
                ;
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONObject json = new JSONObject();

            json.put("aaa", aaa);
            return json.toString();
        }
        return null;

    }


    //添加品牌
    @RequestMapping("addShopBrand")
    @ResponseBody
    public String addShopBrand(ShopBrand shopBrand) {
        try {
            shopService.addShopBrand(shopBrand);
            redisTemplate.delete("shopBrand");
            return "1";
        } catch (Exception e) {
            return "2";
        }
    }

    //商品
    @RequestMapping("goProduct")
    public String goProduct() {
        return "ShopProduct";
    }

    @RequestMapping("addShopProduct")
    @ResponseBody
    public String addShopProduct(ShopProduct shopProduct) {
        try {
            shopService.addShopProduct(shopProduct);
            return "1";
        } catch (Exception e) {
            return "2";
        }
    }
    //查询商品添加品牌
    @RequestMapping("queryBrandId")
    @ResponseBody
    public List queryBrandId(){
        List<ShopBrand>list=shopService.queryBrandId();
        return list;
    }
    //品牌修改回显
    @RequestMapping("updateShopBrand")
    @ResponseBody
    public ShopBrand updateShopBrand(String id){
        ShopBrand shopBrand=shopService.updateShopBrand(id);
        return shopBrand;
    }
}
