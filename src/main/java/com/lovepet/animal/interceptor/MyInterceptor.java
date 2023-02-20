package com.lovepet.animal.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getSession().getAttribute("userId") == null) {
            System.out.println("[頁面訪問事件] 攔截器登入判斷: 尚未登入");
            response.setStatus(302);

            return false;
        }
        return true;


    }
}
