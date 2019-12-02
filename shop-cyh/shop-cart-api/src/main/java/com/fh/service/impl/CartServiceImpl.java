package com.fh.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.model.CartBean;
import com.fh.service.CartService;
import com.fh.utils.HttpclientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public long addCart(String productId, String phone) {
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + phone);
        //redisTemplate.opsForHash().putIfAbsent(key, hashKey, value)
        Boolean hashYN = redisTemplate.opsForHash().hasKey(cartId,productId);
        CartBean car=null;
        String cbJson="";
        if(hashYN){
              car = (CartBean) redisTemplate.opsForHash().get(cartId, productId);
              car.setProductCount(car.getProductCount().intValue()+1);
           /*  cbJson = JSONObject.toJSONString(car);*/
        }else{
            String url=  "http://localhost:9003/httpproductapi/queryProductInfo/"+productId;
            String proInfo = HttpclientUtils.doGet(url);
            JSONObject prodcut = JSON.parseObject(proInfo);
             car=new CartBean();
            car.setId( prodcut.getString("id"));
            car.setPrice( prodcut.getBigDecimal("price"));
            car.setSubtitle( prodcut.getString("subtitle"));
            car.setProductImg( prodcut.getString("mainimg"));
            car.setProductName(prodcut.getString("name"));
            car.setProductCount(1);
           /* cbJson = JSONObject.toJSONString(car);*/


        }
        BigDecimal bigDecimal=BigDecimal.valueOf(0.00);
        BigDecimal count=new BigDecimal(car.getProductCount());
        BigDecimal priceOneAll = bigDecimal.add(car.getPrice()).multiply(count);
        car.setProductAllPrice(priceOneAll);
        car.setProductSelect(true);
        redisTemplate.opsForHash().put(cartId, productId, car);
        Long cartCount=redisTemplate.opsForHash().size(cartId);
        return cartCount;
    }

    @Override
    public Map<String,Object>queryCartInfo(String phone) {
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + phone);
        List <CartBean> values = redisTemplate.opsForHash().values(cartId);
        BigDecimal bigDecimal=BigDecimal.valueOf(0.00);
        Integer allCount=0;
            for (int i=0;i<values.size();i++){
                if(values.get(i).getProductSelect()==true){
                    bigDecimal=bigDecimal.add(values.get(i).getProductAllPrice());
                    allCount=values.get(i).getProductCount()+allCount;
                }
            }
        Map<String,Object>map=new HashMap<>();
        map.put("cartList",values);
        map.put("allPrice",bigDecimal);
        map.put("allCount",allCount);
        return map;
    }

    @Override
    public void deleteCartInfo(String phone, String id) {
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + phone);
        redisTemplate.opsForHash().delete(cartId,id);
    }

    @Override
    public void removeNUmProduct(String phone, String id) {
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + phone);
        CartBean car = (CartBean) redisTemplate.opsForHash().get(cartId, id);
        car.setProductCount(car.getProductCount().intValue()-1);
        redisTemplate.opsForHash().put(cartId, id, car);
    }

    @Override
    public void addProduct(String phone, String id) {
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + phone);
        CartBean  car = (CartBean) redisTemplate.opsForHash().get(cartId, id);
        car.setProductCount(car.getProductCount().intValue()+1);
        redisTemplate.opsForHash().put(cartId, id, car);
    }

    @Override
    public void batchAll(String phone) {
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + phone);
        List <CartBean> values = redisTemplate.opsForHash().values(cartId);
        for (int i=0;i<values.size();i++){
            values.get(i).setProductSelect(!values.get(i).getProductSelect());
            CartBean cartBean=values.get(i);
            redisTemplate.opsForHash().put(cartId,values.get(i).getId(),cartBean);
        }
    }

    @Override
    public void radioCart(String phone, String id) {
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + phone);
        CartBean car = (CartBean) redisTemplate.opsForHash().get(cartId, id);
        car.setProductSelect(!car.getProductSelect());
        redisTemplate.opsForHash().put(cartId, id, car);
    }
}
