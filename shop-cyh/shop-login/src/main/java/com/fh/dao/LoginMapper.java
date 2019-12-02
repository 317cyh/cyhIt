package com.fh.dao;

import com.fh.model.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginMapper {

    UserBean queryUserPhone(String phone);

    void addUser(UserBean userBean1);
}
