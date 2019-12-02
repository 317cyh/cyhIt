package com.fh.service.impl;

import com.fh.dao.ProductDao;
import com.fh.model.ShopProduct;
import com.fh.model.ShopProductPage;
import com.fh.service.ProductService;
import com.fh.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public PageBean<ShopProduct> queryProduct(PageBean<ShopProduct> pageBean, ShopProductPage shopProductPage) {
        Long count=productDao.queryProductCount(shopProductPage);
        pageBean.setRecordsFiltered(count);
        pageBean.setRecordsTotal(count);
        //查询分页数据
        List<ShopProduct> productList=productDao.queryList(pageBean,shopProductPage);
        pageBean.setData(productList);
        return pageBean;

    }

    @Override
    public ShopProduct queryProductInfo(String productId) {
        return productDao.queryProductInfo(productId);
    }
}
