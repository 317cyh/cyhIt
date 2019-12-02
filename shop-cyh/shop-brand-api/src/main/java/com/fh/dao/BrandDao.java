package com.fh.dao;

import com.fh.model.ShopBrand;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BrandDao {
    List<ShopBrand> queryBrandInfo();
}
