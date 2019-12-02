package com.shop.mapper;

import com.shop.model.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TestMapper {

    UserBean userlogin(String loginname);
}
