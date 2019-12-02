package com.fh.service;

import com.fh.model.ShopProduct;
import com.fh.model.ShopProductPage;
import com.fh.utils.PageBean;

public interface ProductService {

    PageBean<ShopProduct> queryProduct(PageBean<ShopProduct> pageBean, ShopProductPage shopProductPage);

    ShopProduct queryProductInfo(String productId);
}
