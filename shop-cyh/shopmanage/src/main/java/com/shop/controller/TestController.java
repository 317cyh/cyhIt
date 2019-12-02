package com.shop.controller;

import com.shop.model.UserBean;
import com.shop.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("test")
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("goLogin")
    public String goLogin() {
        return "lookLeave";
    }

    @RequestMapping("userlogin")
    @ResponseBody
    public String userlogin(UserBean userBean, HttpServletRequest request) {
        String status = "";
      UserBean  user=testService.userlogin(userBean.getLoginname());
        if (user != null) {
            if (user.getLoginpwd().equals(userBean.getLoginpwd())) {
                request.getSession().setAttribute("user", user);
                //登录成功！
                status = "3";
            } else {
                //密码错误
                status = "2";
            }
        } else {
            //账号不存在！
            status = "1";
        }
        return status;
    }
}
