package com.shop.interceptor;

import com.shop.model.UserBean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ConfigPathInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserBean user = (UserBean) request.getSession().getAttribute("user");
        String path = request.getServletPath();
        if (user == null) {
            if (path.equals("/error")) {
                response.sendRedirect("/static/index.html");
                return false;
            } else if (path.equals("/")) {
                response.sendRedirect("/static/index.html");
                return false;
            } else {
                response.sendRedirect("goLogin");
                return false;
            }
        }
        return true;
    }

}
