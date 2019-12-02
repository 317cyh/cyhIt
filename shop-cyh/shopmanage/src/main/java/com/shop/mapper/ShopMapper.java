package com.shop.mapper;

import com.shop.model.ShopBrand;
import com.shop.model.ShopProduct;
import com.shop.model.ShopType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ShopMapper {
    List<ShopType> queryType();

    void addShopType(ShopType shopType);

    void deleteShopType(String id);

    void deleteShopTypeTwo(String id);

    void updateShopType(ShopType shopType);

    Integer queryCountz(ShopBrand shopBrand);

    List<ShopBrand> queryResource(ShopBrand shopBrand);

    void delteShopBrand(String id);

    void addShopBrand(ShopBrand shopBrand);

    void addShopProduct(ShopProduct shopProduct);

    List<ShopBrand> queryBrandId();

    ShopBrand updateShopBrand(String id);
}
