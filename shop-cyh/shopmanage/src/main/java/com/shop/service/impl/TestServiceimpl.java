package com.shop.service.impl;

import com.shop.mapper.TestMapper;
import com.shop.model.UserBean;
import com.shop.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class TestServiceimpl implements TestService {
    @Autowired
    private TestMapper testMapper;

    @Override
    public UserBean userlogin(String loginname) {
        return testMapper.userlogin(loginname);
    }
}
