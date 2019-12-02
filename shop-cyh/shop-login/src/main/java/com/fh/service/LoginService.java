package com.fh.service;

import com.fh.model.UserBean;

public interface LoginService {
    UserBean queryUserPhone(String phone);

    void addUser(UserBean userBean1);
}
