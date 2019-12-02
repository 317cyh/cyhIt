package com.fh.service;

import java.util.Map;

public interface CartService {
    long addCart(String productId, String phone);

    Map<String,Object> queryCartInfo(String phone);

    void deleteCartInfo(String phone, String id);

    void removeNUmProduct(String phone,String id);

    void addProduct(String phone,String id);

    void batchAll(String phone);

    void radioCart(String phone, String id);
}
