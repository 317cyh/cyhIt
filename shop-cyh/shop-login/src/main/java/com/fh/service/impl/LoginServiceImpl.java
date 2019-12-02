package com.fh.service.impl;

import com.fh.dao.LoginMapper;
import com.fh.model.UserBean;
import com.fh.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public UserBean queryUserPhone(String phone) {
        return loginMapper.queryUserPhone(phone);
    }

    @Override
    public void addUser(UserBean userBean1) {
        loginMapper.addUser(userBean1);
    }
}

