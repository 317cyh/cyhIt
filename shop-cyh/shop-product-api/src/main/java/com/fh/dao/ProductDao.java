package com.fh.dao;

import com.fh.model.ShopProduct;
import com.fh.model.ShopProductPage;
import com.fh.utils.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductDao {
    Long queryProductCount(@Param("shopProductPage") ShopProductPage shopProductPage);

    List<ShopProduct> queryList(@Param("pageBean")PageBean<ShopProduct> pageBean, @Param("shopProductPage")ShopProductPage shopProductPage);

    ShopProduct queryProductInfo(String productId);
}
